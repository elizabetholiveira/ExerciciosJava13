package exer02;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner resposta = new Scanner(System.in);

        boolean resetar = true;

        while (resetar) {
            DolarAmericano dolarAmericano = new DolarAmericano();
            DolarCanadense dolarCanadense = new DolarCanadense();
            Euro euro = new Euro();
            LibraEsterlina libraEsterlina = new LibraEsterlina();

            System.out.println("Boas-vindas ao conversor de moedas!");

            System.out.println("Qual moeda você deseja converter?");
            System.out.println("1) Dólar americano");
            System.out.println("2) Dólar canadense");
            System.out.println("3) Euro");
            System.out.println("4) Libra Esterlina");
            int selecaoMoeda = resposta.nextInt();

            String moeda = null;
            String codigo = null;
            String valorMoeda = null;

            try {
                switch (selecaoMoeda) {
                    case 1:
                        moeda = dolarAmericano.getNomeMoeda();
                        valorMoeda = dolarAmericano.getValor();
                        codigo = dolarAmericano.getCodigo();
                        break;
                    case 2:
                        moeda = dolarCanadense.getNomeMoeda();
                        valorMoeda = dolarCanadense.getValor();
                        codigo = dolarCanadense.getCodigo();
                        break;
                    case 3:
                        moeda = euro.getNomeMoeda();
                        valorMoeda = euro.getValor();
                        codigo = euro.getCodigo();
                        break;
                    case 4:
                        moeda = libraEsterlina.getNomeMoeda();
                        valorMoeda = libraEsterlina.getValor();
                        codigo = libraEsterlina.getCodigo();
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de moeda inválido");
            }

            System.out.println("No momento, só consigo converter para Real (R$)");

            System.out.println("Você selecionou: " + moeda);

            boolean inserirValor = true;
            String respostaValorConverter = null;

            while (inserirValor) {
                try {
                    System.out.println("Digite o valor a ser convertido:");
                    respostaValorConverter = resposta.next();
                    BigDecimal valorConverter = new BigDecimal(respostaValorConverter);
                    if (valorConverter.compareTo(BigDecimal.ZERO) <= 0) {
                        System.out.println("Valor inválido, por favor, tente novamente");
                    } else {
                        inserirValor = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Para moeda, o valor monetário deve ser um número decimal");
                }
            }

            //Converter a moeda aqui

            BigDecimal valorConverter = new BigDecimal(respostaValorConverter);
            BigDecimal valor = new BigDecimal(valorMoeda);
            BigDecimal valorConvertido = valorConverter.divide(valor, 2, RoundingMode.HALF_UP);

            LocalDate hoje = LocalDate.now();

            System.out.println("A moeda " + moeda + " na cotação de hoje (" + hoje.getDayOfMonth() + ") está em " + valor + " " + codigo + ", o valor que pediu para" +
                    " converter de " + valorConverter + " " + codigo + " em reais é R$ " + valorConvertido);

            System.out.println();
            System.out.println("Deseja fazer uma nova conversão? (digite 'n' para sair)");
            String deNovo = resposta.next();

            if (deNovo.equalsIgnoreCase("n")) {
                resetar = false;
            }
        }
    }
}
