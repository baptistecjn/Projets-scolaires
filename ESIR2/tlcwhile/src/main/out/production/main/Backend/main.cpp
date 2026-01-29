#include "while_runtime.h"
#include <stack>
#include <vector>
#include <iostream>

typedef Tree TreeNode;

TreeNode* sub(TreeNode* Op1, TreeNode* Op2) {
    TreeNode* R1 = Op1;
    TreeNode* R2 = Op2;
    TreeNode* Result = nullptr;
    TreeNode* R3 = Result;
    R3 = Op1;
   L1: if (Op2 == nullptr) {
    R3 = tl(Result);
    Op2 = tl(Op2);
   goto L1;
}
    return Result;
}


int main() {
    std::vector<int> numbers;
    std::string input;

    std::cout << "Entrez des nombres . Pour terminer, entrez 'fin' : " << std::endl;

    while (true) {
        std::getline(std::cin, input);
        if (input == "fin") {
            break;
        }
        TreeNode* Result = int_to_tree(std::stoi(input));
    std::cout << "Voici le retour : " << std::endl;
        pp(Result);
    std::cout << "Entrez un nouveau nombre : " << std::endl;
    }
}
