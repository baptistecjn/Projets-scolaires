package while_compiler.semantic;

import org.antlr.runtime.tree.CommonTree;
import java.util.HashSet;
import java.util.Set;
import antlr_grammar.While_astParser;

public class SemanticAnalyzer {

    private final SymbolTable symbolTable;

    public SemanticAnalyzer() {
        this.symbolTable = new SymbolTable();
    }

    public SymbolTable analyze(CommonTree tree) throws SemanticException {
        scanFunctionSignatures(tree);
        analyzeFunctionBodies(tree);
        return symbolTable;
    }

    private void scanFunctionSignatures(CommonTree tree) throws SemanticException {
        for (int i = 0; i < tree.getChildCount(); i++) {
            CommonTree child = (CommonTree) tree.getChild(i);
            if (child.getToken().getType() == While_astParser.FUNC) {
                String funcName = child.getChild(0).toStringTree();
                CommonTree param = (CommonTree) child.getChild(1);
                CommonTree result = (CommonTree) child.getChild(3);
                int nbInputs =param.getChild(0).getChildCount();
                int nbOutputs = result.getChild(0).getChildCount();
                FunctionSymbol func = new FunctionSymbol(funcName, nbInputs, nbOutputs);
                symbolTable.addFunction(func);
            }
        }
    }


    private void analyzeFunctionBodies(CommonTree tree) throws SemanticException {
        for (int i = 0; i < tree.getChildCount(); i++) {
            CommonTree child = (CommonTree) tree.getChild(i);
            if (child.getType() == While_astParser.FUNC) {
                analyzeFunction(child);
            }
        }
    }

    private void analyzeFunction(CommonTree funcNode) throws SemanticException {
        String funcName = funcNode.getChild(0).getText();
        FunctionSymbol currentFunc = symbolTable.getFunction(funcName);
        symbolTable.enterFunctionScope();
        int memoryOffset = 0;
        CommonTree inputNode = (CommonTree) funcNode.getChild(1).getChild(0);
        Set<String> inputs = new HashSet<>();
        for (int i = 0; i < inputNode.getChildCount(); i++) {
            String varName = inputNode.getChild(i).getText();
            if (inputs.contains(varName)) {
                throw new SemanticException("Paramètre dupliqué '" + varName + "' dans " + funcName);
            }
            inputs.add(varName);
            declareVariable(varName, currentFunc, memoryOffset++);
        }

        CommonTree outputNode = (CommonTree) funcNode.getChild(3).getChild(0);
        Set<String> output = new HashSet<>();
        for (int i = 0; i < outputNode.getChildCount(); i++) {
            String varName = outputNode.getChild(i).getText();
            if (symbolTable.getLocalVariable(varName) == null) {
                declareVariable(varName, currentFunc, memoryOffset++);
            }
            if (output.contains(varName)) {
                throw new SemanticException("Paramètre dupliqué '" + varName + "' dans " + funcName);
            }
            output.add(varName);
        }

        CommonTree bodyNode = (CommonTree) funcNode.getChild(2);
        analyzeCommands(bodyNode, currentFunc);
    }

    private void analyzeCommands(CommonTree node, FunctionSymbol currentFunc) throws SemanticException {
        if (node == null) return;

        if (node.getType() == While_astParser.BLOCK) {
            for (int i = 0; i < node.getChildCount(); i++) {
                analyzeCommands((CommonTree) node.getChild(i), currentFunc);
            }
            return;
        }

        switch (node.getType()) {
            case While_astParser.ASSIGN_NODE:
                analyzeAssignment(node, currentFunc);
                break;

            case While_astParser.CALL:
                analyzeCall(node);
                break;

            default:
                String text = node.getText();
                if ("if".equals(text)) {
                    analyzeExpression((CommonTree) node.getChild(0));
                    analyzeCommands((CommonTree) node.getChild(1), currentFunc);
                    if (node.getChildCount() > 2) analyzeCommands((CommonTree) node.getChild(2), currentFunc);
                }
                else if ("while".equals(text)) {
                    analyzeExpression((CommonTree) node.getChild(0));
                    analyzeCommands((CommonTree) node.getChild(1), currentFunc);
                }
                else if ("for".equals(text)) {
                    analyzeExpression((CommonTree) node.getChild(0));
                    analyzeCommands((CommonTree) node.getChild(1), currentFunc);
                }
                else if ("foreach".equals(text)) {
                    String varName = node.getChild(0).getText();
                    declareVariable(varName, currentFunc, 0);
                    analyzeExpression((CommonTree) node.getChild(1));
                    analyzeCommands((CommonTree) node.getChild(2), currentFunc);
                }
                else {
                     for (int i = 0; i < node.getChildCount(); i++) {
                        analyzeCommands((CommonTree) node.getChild(i), currentFunc);
                    }
                }
                break;
        }
    }

    private void analyzeAssignment(CommonTree node, FunctionSymbol currentFunc) throws SemanticException {
        if (node.getChild(0).getChildCount()!=node.getChild(1).getChildCount()) {
            throw new SemanticException("Impossible d'assigner plusieurs élements à une variable");
        }else{
            CommonTree exprs = (CommonTree) node.getChild(1);
            if (exprs != null) {
                    if (exprs.getType() == While_astParser.LIST) {
                        for(int i=0; i<exprs.getChildCount(); i++)
                            analyzeExpression((CommonTree) exprs.getChild(i));
                    } else {
                        analyzeExpression(exprs);
                    }
                }
        }

        CommonTree vars = (CommonTree) node.getChild(0);
        declareVarsInTree(vars, currentFunc);
    }

    private void analyzeCall(CommonTree node) throws SemanticException {
        String funcName = node.getChild(0).getText();
        FunctionSymbol func = symbolTable.getFunction(funcName);

        if (func == null) {
            throw new SemanticException("Fonction inconnue : " + funcName);
        }

        int argsCount = node.getChildCount() - 1;
        if (argsCount != func.getNbInputs()) {
            throw new SemanticException("Appel à " + funcName + ": " + argsCount + " args fournis, " + func.getNbInputs() + " attendus.");
        }

        for(int i = 1; i < node.getChildCount(); i++) {
            analyzeExpression((CommonTree) node.getChild(i));
        }
    }

    private void analyzeExpression(CommonTree node) throws SemanticException {

        if (node == null) return;

        int type = node.getType();
        if (node.getChildCount() == 0) {
            String text = node.getText();
            
            if (type == While_astParser.NIL || text.equals("nil")) {
                return;
            }

            VariableSymbol var = symbolTable.getLocalVariable(text);
            if (var == null) {
                throw new SemanticException("Variable non définie : " + text);
            }
        } else {
            if (type == While_astParser.CALL) {
                analyzeCall(node);
            } else {
                for (int i = 0; i < node.getChildCount(); i++) {
                    analyzeExpression((CommonTree) node.getChild(i));
                }
            }
        }    
    }

    private void declareVarsInTree(CommonTree node, FunctionSymbol currentFunc) {
        if (node.getType() == While_astParser.VARS) {
            String name = node.getChild(0).getText();
            if (symbolTable.getLocalVariable(name) == null) {
                int nextIndex = currentFunc.getLocalVariableNames().size();
                declareVariable(name, currentFunc, nextIndex);
            }
        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                declareVarsInTree((CommonTree) node.getChild(i), currentFunc);
            }
        }
    }

    private void declareVariable(String name, FunctionSymbol currentFunc, int index) {
        VariableSymbol var = new VariableSymbol(name);
        var.setMemoryIndex(index);
        symbolTable.addLocalVariable(var);
        currentFunc.addLocalVariable(name);
    }
}