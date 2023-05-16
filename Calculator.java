public class Calculator extends MyCustomStack {


    private static int arithmeticExpression (String text)
    {
        MyCustomStack <Integer> operands = new MyCustomStack <>(text.length());
        MyCustomStack <Character> operators = new MyCustomStack <>(text.length());

        for (int i = 0; i < text.length(); i++)
            {
                char character = text.charAt(i);

                if (Character.isDigit(character))
                {
                    int operand = 0;

                    while (i < text.length() && Character.isDigit(text.charAt(i)))
                    {
                        operand = operand * 10 + (text.charAt(i) - '0');
                        i++;
                    }
                    i--;
                    System.out.print(operand);
                    operands.push(operand);

                }

                else if (character == '(')
                {
                    operators.push(character);
                }
                else if (character == ')')
                {
                    while (operators.peek() != '(')
                    {
                        char operator = operators.pop();
                        int operandAbove = operands.pop();
                        int operandBelow = operands.pop();
                        int result = arithmeticOperation(operator, operandBelow, operandAbove);

                        operands.push(result);
                    }

                    operators.pop();
                }

                else if (character == '+' || character == '-' || character == '*' || character == '/' || character == '^' || character == '<' || character == '>' || character == '=' || character == '!' || character == '≥' || character == '≤')
                {

                    while (!operators.isEmpty() && operatorPrecedence(character) >= operatorPrecedence(operators.peek()) && operands.size() >= 2) {
                        /**
                         * if the operators stack is empty then this is false, it it is not empty then it is true
                         */
                        char operator = operators.pop();
                        int operandAbove = operands.pop();
                        int operandBelow = operands.pop();
                        int result = arithmeticOperation(operator, operandBelow, operandAbove);

                        operands.push(result);
                    }
                    System.out.print(character);
                    operators.push(character);


                }



            }

            while (!operators.isEmpty() && operands.size() >= 2)

            {
                char operator = operators.pop();
                int operandAbove = operands.pop();
                int operandBelow = operands.pop();
                int result = arithmeticOperation(operator, operandBelow, operandAbove);

                operands.push(result);
            }

            return operands.pop();
            /**
             * this returns the result of the arithmetic expression, 1 or 0 if true or false, anything else is a decimal value result for the
             * arithmetic expression
             */



        }






        private static int operatorPrecedence(char operator) { //this checks the precedence of the operators

            switch (operator) {
                case '^':
                    return 1;
                case '*','/':
                    return 2;
                case '+', '-':
                    return 3;
                case '>', '<', '≥', '≤':
                    return 4;
                case '=', '!':
                    return 5;
                default:
                    return -1; //if none of the character this -1 is returned
            }




        }

        private static int arithmeticOperation (char operator, int operandBelow, int operandAbove) {

            switch(operator)
            {
                case '^':
                    return (int) Math.pow(operandBelow, operandAbove);
                case '*':
                    return operandBelow * operandAbove;
                case '/':
                    return operandBelow/operandAbove;
                case '+':
                    return operandBelow + operandAbove;
                case '-':
                    return operandBelow - operandAbove;
                case '>':
                    return operandBelow > operandAbove ? 1 : 0;
                case '<':
                    return operandBelow < operandAbove ? 1 : 0;
                case '≥':
                    return operandBelow >= operandAbove ? 1 : 0;
                case '≤':
                    return operandBelow <= operandAbove ? 1 : 0;
                case '=':
                    return operandBelow == operandAbove ? 1 : 0;
                case '!':
                    return operandBelow != operandAbove ? 1 : 0;
                default:
                    return 0;
            }


        }






        public static void main(String[] args) {

            String [] array = new String [20];


            array[0] = "(2+3)*(4-1) > (2^2+11)";
            array[1] = "5*3>3^2";
            array[2] = "(350+200) !=120";
            array[3] = "(33+0)==33";
            array[4] = "(2+4)/7<9";
            array[5] = "(2)^2≤100";
            array[6] = "(2-3*7)/7 ≤ 2";
            array[7] = "2 * 9 ≥ 1";
            array[8] = "33 * 4 - 2 ≥ 35 ^ 2";
            array[9] = "923 - 232 > 232";
            array[10] = "(122* 3 ) ^ 2 > 254";
            array[11] = "25/1==25-15-5";
            array[12] = "232+232 * 4 > 324";
            array[13] = "34 + 23 - 7 < 2000";
            array[14] = "123+23 < 372 + 23";
            array[15] = "23-22/2 < 9000";
            array[16] = "22+90-23/36*22";
            array[17] = "4+2*9-5";
            array[18] = "2 + 7 + (2*3) - 9";
            array[19] = "12 + 8 - 3 * 7";


            for (int i = 0; i < 20; i++)
            {

                int result = arithmeticExpression(array[i]);

                if (result == 0)
                {
                    System.out.println("The arithmetic expression is false");
                }
                else if (result == 1)
                {
                    System.out.println("The arithmetic expression is true");
                }
                else
                {
                    System.out.println("The arithmetic expression equals "+ result);
                }


            }



        }

}

