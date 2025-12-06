#include <iostream>

int main(int argc, char** argv){
    
    const char* caractere = "|";    
    int n = 0;

    std::cout<<"entrer un nombre pour la taille du sapin";
    std::cin>>n;

    for(int i=1; i<=n; i++){
        for(int k=0;k<=(n-i)/2;k++){
            std::cout<<" ";
        }
        for(int j=0; j<=i;j++){

            std::cout<<caractere;
        }

        std::cout<<std::endl;

    }

    return 0;

}