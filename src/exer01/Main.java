package exer01;

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
            System.out.println("Digite o tipo de alimento que deseja adicionar (Verdura / Legumes / Grãos / Outros)");
            String tipoAlimento = resposta.next();

            int codigoAlimento = 0;

            try {
                if (tipoAlimento.equalsIgnoreCase("Verdura") || tipoAlimento.equalsIgnoreCase("Verduras")) {
                    codigoAlimento = 1;
                } else if (tipoAlimento.equalsIgnoreCase("Legume") || tipoAlimento.equalsIgnoreCase("Legumes")) {
                    codigoAlimento = 2;
                } else if (tipoAlimento.equalsIgnoreCase("Grão") || tipoAlimento.equalsIgnoreCase("Grãos")) {
                    codigoAlimento = 3;
                } else if (tipoAlimento.equalsIgnoreCase("Outro") || tipoAlimento.equalsIgnoreCase("Outros")) {
                    codigoAlimento = 4;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de alimento inválido");
            }

            boolean repetir = true;

            while (repetir) {

                //Se o usuário escolher VERDURA ou GRÃOS, deve informar um double da quantidade em gramas

                try {
                    if (codigoAlimento == 1 || codigoAlimento == 3) {
                        System.out.println("Insira a quantidade em gramas:");
                        String entrada = resposta.next();
                        if (entrada.isEmpty()) {
                            throw new UnsupportedOperationException();
                        } else {
                            double qtdGramas = Double.parseDouble(entrada);
                            if (qtdGramas == Math.floor(qtdGramas)) {
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
            String nomeAlimento = resposta.next();
            resposta.nextLine();

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

            System.out.println("Deseja inserir mais um item na lista? (s/n)");
            String deNovo = resposta.next();

            if (deNovo.equalsIgnoreCase("s")){

            } else if (deNovo.equalsIgnoreCase("n")){
                lista = false;
            } else {
                lista = false;
            }

        }

        System.out.println("Lista de compras:");

        if (nomesVerduras.isEmpty()){

        } else {
            System.out.println("VERDURAS:");
            System.out.print("[");
            for (int i = 0; i < nomesVerduras.size(); i++){
                System.out.print(nomesVerduras.get(i) + "-" + qtdVerduras.get(i) + " kg");
                if (i + 1 < nomesVerduras.size()){
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            System.out.println();
        }

        if (nomesLegumes.isEmpty()){

        } else {
            System.out.println("LEGUMES:");
        }

        if (nomesGraos.isEmpty()){

        } else {
            System.out.println("GRÃOS:");
        }

        if (nomesOutros.isEmpty()){

        } else {
            System.out.println("OUTROS:");
        }
    }
}
