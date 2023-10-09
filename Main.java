import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        AVLTree avlTree = new AVLTree();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quantidade de números a serem inseridos: ");
        int n = scanner.nextInt();
        
        System.out.println("Operações para " + n + " elementos:");

        long startTimebin = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int randomNumber = random.nextInt(1000);
            binaryTree.insert(randomNumber);
        }
        long endTimebin = System.nanoTime();
        long insertionTimeBin = (endTimebin - startTimebin); 
        System.out.println("Inserção (BinaryTree): " + insertionTimeBin + " nanoseconds");


        long startTimeAvl = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int randomNumber = random.nextInt(1000);
            avlTree.insert(randomNumber);
        }
        long endTimeAvl = System.nanoTime();
        long insertionTimeAvl = (endTimeAvl - startTimeAvl); 
        System.out.println("Inserção (AVLTree): " + insertionTimeAvl + " nanoseconds");

        // Loop para operações
        while (true) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1. Consultar um número");
            System.out.println("2. Remover um número");
            System.out.println("3. Sair");
            System.out.print("Opção: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // Consultar
                System.out.print("Digite o número para consultar: ");
                int searchValue = scanner.nextInt();
                searchElement(binaryTree, avlTree, searchValue);
            } else if (choice == 2) {
                // Remover
                System.out.print("Digite o número para remover: ");
                int removeValue = scanner.nextInt();
                removeElement(binaryTree, avlTree, removeValue);
            } else if (choice == 3) {
                break; // Sair do loop
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void searchElement(BinaryTree binaryTree, AVLTree avlTree, int searchValue) {
        long startTime = System.nanoTime();
        boolean foundBinaryTree = binaryTree.search(searchValue);
        long endTime = System.nanoTime();
        long searchTimeBinaryTree = (endTime - startTime);
        System.out.println("Busca (BinaryTree): " + searchTimeBinaryTree + " nanoseconds, Encontrado? " + foundBinaryTree);

        startTime = System.nanoTime();
        boolean foundAVLTree = avlTree.search(searchValue);
        endTime = System.nanoTime();
        long searchTimeAVLTree = (endTime - startTime); 
        System.out.println("Busca (AVLTree): " + searchTimeAVLTree + " nanoseconds, Encontrado? " + foundAVLTree);
    }

    private static void removeElement(BinaryTree binaryTree, AVLTree avlTree, int removeValue) {
        long startTime = System.nanoTime();
        binaryTree.remove(removeValue);
        long endTime = System.nanoTime();
        long removalTimeBinaryTree = (endTime - startTime); 
        System.out.println("Remoção (BinaryTree): " + removalTimeBinaryTree + " nanoseconds");

        startTime = System.nanoTime();
        avlTree.remove(removeValue);
        endTime = System.nanoTime();
        long removalTimeAVLTree = (endTime - startTime); 
        System.out.println("Remoção (AVLTree): " + removalTimeAVLTree + " nanoseconds");
    }
}
