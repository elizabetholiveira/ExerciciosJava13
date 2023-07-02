package exer01;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Program de controle de lista de compras

        //Menu que peça para o usuário definir o tipo de alimento que ela quer incluir na lista
        //O usuário pode incluir: VERDURA, LEGUMES, GRÃOS e OUTROS

        Scanner resposta = new Scanner(System.in);

        List<String> nomesVerduras = new ArrayList<>();
        List<String> nomesLegumes = new ArrayList<>();
        List<String> nomesGraos = new ArrayList<>();
        List<String> nomesOutros = new ArrayList<>();

        List<Double> qtdVerduras = new ArrayList<>();
        List<Integer> qtdLegumes = new ArrayList<>();
        List<Double> qtdGraos = new ArrayList<>();
        List<Integer> qtdOutros = new ArrayList<>();

        boolean lista = true;

        while (lista) {

            int codigoAlimento = 0;
            boolean escreva = true;

           while (escreva) { //Tive que botar isso porque ele simplesmente não tava resetando quando dava Tipo de alimento inválido
                System.out.println("Digite o tipo de alimento que deseja adicionar (Verdura / Legumes / Grãos / Outros)");
                String tipoAlimento = resposta.nextLine();

                try {
                    if (tipoAlimento.equalsIgnoreCase("Verdura") || tipoAlimento.equalsIgnoreCase("Verduras")) {
                        codigoAlimento = 1;
                        escreva = false;
                    } else if (tipoAlimento.equalsIgnoreCase("Legume") || tipoAlimento.equalsIgnoreCase("Legumes")) {
                        codigoAlimento = 2;
                       escreva = false;
                    } else if (tipoAlimento.equalsIgnoreCase("Grão") || tipoAlimento.equalsIgnoreCase("Grãos")) {
                        codigoAlimento = 3;
                        escreva = false;
                    } else if (tipoAlimento.equalsIgnoreCase("Outro") || tipoAlimento.equalsIgnoreCase("Outros")) {
                        codigoAlimento = 4;
                        escreva = false;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Tipo de alimento inválido");
                }
            }

            boolean repetir = true;

            while (repetir) {

                //Se o usuário escolher VERDURA ou GRÃOS, deve informar um double da quantidade em gramas

                try {
                    if (codigoAlimento == 1 || codigoAlimento == 3) {
                        System.out.println("Insira a quantidade em gramas:");
                        String entrada = resposta.next();
                        BigDecimal bigQtdArredondada = new BigDecimal(entrada).setScale(1, RoundingMode.UNNECESSARY);
                        BigDecimal bigQtd = new BigDecimal(entrada);
                        boolean saoIguais = bigQtdArredondada.equals(bigQtd);
                        if (entrada.isEmpty()) {
                            throw new UnsupportedOperationException();
                        } else {
                            double qtdGramas = Double.parseDouble(entrada);
                            if (!saoIguais) {
                                throw new NumberFormatException();
                            }
                            if (qtdGramas < 0) {
                                System.out.println("Não é possível inserir números negativos, por favor tente de novo");
                            } else {
                                if (codigoAlimento == 1) {
                                    qtdVerduras.add(qtdGramas);
                                }
                                if (codigoAlimento == 3) {
                                    qtdGraos.add(qtdGramas);
                                }
                                repetir = false;
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    if (codigoAlimento == 1) {
                        System.out.println("Para verdura, a quantidade deve ser informada com ponto");
                    }
                    if (codigoAlimento == 3) {
                        System.out.println("Para grãos, a quantidade deve ser informada com ponto");
                    }
                }


                //Se o usuário escolher LEGUME ou OUTROS, deve informar um int da quantidade em unidades

                try {
                    if (codigoAlimento == 2 || codigoAlimento == 4) {
                        System.out.println("Insira a quantidade em unidades:");
                        String entrada = resposta.next();
                        if (entrada.isEmpty()) {
                            throw new UnsupportedOperationException();
                        } else {
                            double qtdUnidadesDouble = Double.parseDouble(entrada);
                            if (qtdUnidadesDouble != Math.floor(qtdUnidadesDouble)) {
                                throw new NumberFormatException();
                            }
                            if (qtdUnidadesDouble < 0) {
                                System.out.println("Não é possível inserir números negativos, por favor tente de novo");
                            } else {
                                int qtdUnidades = Integer.parseInt(entrada);
                                if (codigoAlimento == 2) {
                                    qtdLegumes.add(qtdUnidades);
                                }
                                if (codigoAlimento == 4) {
                                    qtdOutros.add(qtdUnidades);
                                }
                                repetir = false;
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    if (codigoAlimento == 2) {
                        System.out.println("Para legume, a quantidade deve ser informada em unidades inteiras");
                    }
                    if (codigoAlimento == 4) {
                        System.out.println("Para outros, a quantidade deve ser informada em unidades inteiras");
                    }
                }
            }

            //Após inserir a quantidade, o usuário deve inserir o nome do alimento

            System.out.println("Escreva o nome do alimento comprado:");
            resposta.nextLine();
            String nomeAlimento = resposta.nextLine();

            try {
                if (nomeAlimento.isEmpty()) {
                    throw new UnsupportedOperationException();
                } else {
                    if (codigoAlimento == 1) {
                        nomesVerduras.add(nomeAlimento);
                    }
                    if (codigoAlimento == 2) {
                        nomesLegumes.add(nomeAlimento);
                    }
                    if (codigoAlimento == 3) {
                        nomesGraos.add(nomeAlimento);
                    }
                    if (codigoAlimento == 4) {
                        nomesOutros.add(nomeAlimento);
                    }
                }
            } catch (UnsupportedOperationException e) {
                System.out.println("Não é permitido inserir valor vazio");
            }

            System.out.println("Deseja inserir mais um item na lista? (digite 'n' para sair)");
            String deNovo = resposta.next();

            if (deNovo.equalsIgnoreCase("n")){
                lista = false;
            }

            resposta.nextLine(); //Isso tem que ficar aqui pq senão manda o s pro tipoAlimento

        }

        //É impossível printar exatamente como pede no exercício porque Legumes é com unidade e Verduras é com quilo, e não o contrário

        System.out.println("Lista de compras:");
        System.out.println();
        System.out.println("----------");
        System.out.println();

        //---

        if (nomesVerduras.isEmpty()){

        } else {
            System.out.println("Verduras:");
            System.out.print("[");
            for (int i = 0; i < nomesVerduras.size(); i++){
                System.out.print(nomesVerduras.get(i) + " - " + qtdVerduras.get(i) + " kg");
                if (i + 1 < nomesVerduras.size()){
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            System.out.println();
            System.out.println("A quantidade de alimentos do tipo verdura a ser comprada é: " + nomesVerduras.size());
            System.out.println();
            System.out.println("----------");
            System.out.println();
        }

        //---

        if (nomesLegumes.isEmpty()){

        } else {
            System.out.println("Legumes:");
            System.out.print("[");
            for (int i = 0; i < nomesLegumes.size(); i++){
                System.out.print(nomesLegumes.get(i) + " - " + qtdLegumes.get(i) + " unidades");
                if (i + 1 < nomesLegumes.size()){
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            System.out.println();
            System.out.println("A quantidade de alimentos do tipo legumes a ser comprada é: " + nomesLegumes.size());
            System.out.println();
            System.out.println("----------");
            System.out.println();
        }

        //---

        if (nomesGraos.isEmpty()){

        } else {
            System.out.println("Grãos:");
            System.out.print("[");
            for (int i = 0; i < nomesGraos.size(); i++){
                System.out.print(nomesGraos.get(i) + " - " + qtdGraos.get(i) + " kg");
                if (i + 1 < nomesGraos.size()){
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            System.out.println();
            System.out.println("A quantidade de alimentos do tipo grãos a ser comprada é: " + nomesGraos.size());
            System.out.println();
            System.out.println("----------");
            System.out.println();
        }

        //---

        if (nomesOutros.isEmpty()){

        } else {
            System.out.println("Outros:");
            System.out.print("[");
            for (int i = 0; i < nomesOutros.size(); i++){
                System.out.print(nomesOutros.get(i) + " - " + qtdOutros.get(i) + " unidades");
                if (i + 1 < nomesOutros.size()){
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            System.out.println();
            System.out.println("A quantidade de alimentos do tipo outros a ser comprada é: " + nomesOutros.size());
            System.out.println();
            System.out.println("----------");
            System.out.println();
        }
    }
}
