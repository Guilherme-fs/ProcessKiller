package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KillController {
    private String so(){
        String nome = System.getProperty("os.name");
        return nome;
    }

    public void listaProcessos(){
        String chamadaListaProcessos="";
        if (so().toLowerCase().contains("windows")) {
            chamadaListaProcessos = "TASKLIST /FO TABLE";
        } else if (so().toLowerCase().contains("linux")) {
            chamadaListaProcessos = "ps -ef";
        }else{
            System.out.println("Sistema Operacional não identificado.");
            return;
        }
        try{
            if (so().toLowerCase().contains("windows")) {
                Process process = Runtime.getRuntime().exec(chamadaListaProcessos);
                BufferedReader leitor = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String linha;
                while((linha = leitor.readLine()) != null){
                    System.out.println(linha);
                }
                leitor.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mataPid(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o pid do processo que deseja matar: ");
        String pid = scanner.nextLine();
        String chamadaMatarProcesso="";
        if (so().toLowerCase().contains("windows")) {
            chamadaMatarProcesso = "TASKKILL /PID ";
        } else if (so().toLowerCase().contains("linux")) {
            chamadaMatarProcesso = "kill -9 ";
        }else{
            System.out.println("Sistema Operacional não identificado.");
            return;
        }
        try {
            Process process = Runtime.getRuntime().exec(chamadaMatarProcesso+pid);
            // saída normal
            BufferedReader leitor = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String linha;
            while((linha = leitor.readLine()) != null){
                System.out.println(linha);
            }
            leitor.close();
            // saída de erro
            BufferedReader erro = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((linha = erro.readLine()) != null) {
                System.out.println(linha);
            }
            erro.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mataNome(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome, junto com a extenção, do processo que deseja matar: ");
        String nome = scanner.nextLine();
        String chamadaMatarProcesso="";
        if (so().toLowerCase().contains("windows")) {
            chamadaMatarProcesso = "TASKKILL /IM ";
        } else if (so().toLowerCase().contains("linux")) {
            //Linux:pkill -f nome_do_processo
            chamadaMatarProcesso = "pkill -f ";
        }else{
            System.out.println("Sistema Operacional não identificado.");
            return;
        }
        try {
            //Windows:TASKKILL /IM nome_do_processo
            Process process = Runtime.getRuntime().exec(chamadaMatarProcesso+nome);
            // saída normal
            BufferedReader leitor = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String linha;
            while((linha = leitor.readLine()) != null){
                System.out.println(linha);
            }
            leitor.close();
            // saída de erro
            BufferedReader erro = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((linha = erro.readLine()) != null) {
                System.out.println(linha);
            }
            erro.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
