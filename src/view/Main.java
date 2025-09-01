package view;

import controller.KillController;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        KillController kc = new KillController();

        int opcaoEscolhida = 0;
        while(opcaoEscolhida!=3){
            opcaoEscolhida = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção:\n" +
                    "1 - Listar processos\n" +
                    "2 - Matar processo por pid\n" +
                    "3 - Matar processo por nome\n" +
                    "4 - Sair"));
            switch (opcaoEscolhida){
                case 1:
                    kc.listaProcessos();
                    break;
                case 2:
                    kc.mataPid();;
                    break;
                case 3:
                    kc.mataNome();
                    break;
            }
        }
    }
}