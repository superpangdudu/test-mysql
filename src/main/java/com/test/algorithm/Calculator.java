package com.test.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 将中缀表达式转换为后缀表达式
 1. 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
 2. 从左至右扫描中缀表达式；
 3. 遇到操作数时，将其压s2；
 4. 遇到运算符时，比较其与s1栈顶运算符的优先级：
   4.1 如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
   4.2 否则，若优先级比栈顶运算符的高，也将运算符压入s1（注意转换为前缀表达式时是优先级较高或相同，而这里则不包括相同的情况）；
   4.3 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
 5. 遇到括号时：
   5.1 如果是左括号“(”，则直接压入s1；
   5.2 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃；
 6. 重复步骤2至5，直到表达式的最右边；
 7. 将s1中剩余的运算符依次弹出并压入s2；
 8. 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式（转换为前缀表达式时不用逆序）
 */
public class Calculator {

    //
    static class Operation {
        private static int LOW_PRIORITY = 0;
        private static int HIGH_PRIORITY = 1;

        //
        static int ADD = 0;
        static int SUB = 1;
        static int MUL = 2;
        static int DIV = 3;
        static int LEFT_BRACE = 4;
        static int RIGHT_BRACE = 5;

        //
        private int priority;
        private int op;

        Operation(int priority, int op) {
            this.priority = priority;
            this.op = op;
        }

        int getOp() {
            return op;
        }

        static boolean isPrior(Operation left, Operation right) {
            return left.priority > right.priority;
        }

        static Operation newOperation(String c) {
            if (c.equals("+"))
                return new Operation(LOW_PRIORITY, ADD);
            else if (c.equals("-"))
                return new Operation(LOW_PRIORITY, SUB);
            else if (c.equals("*"))
                return new Operation(HIGH_PRIORITY, MUL);
            else if (c.equals("/"))
                return new Operation(HIGH_PRIORITY, DIV);
            else if (c.equals("("))
                return new Operation(HIGH_PRIORITY, LEFT_BRACE);
            else if (c.equals(")"))
                return new Operation(HIGH_PRIORITY, RIGHT_BRACE);

            return null;
        }
    }

    //===============================================================

    /**
     *
     * @param statement
     * @return
     */
    private List<String> parseStatement(String statement) {
        statement = statement.replace(" ", "");
        char[] chars = statement.toCharArray();
        int pos = 0;

        List<String> items = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (pos < chars.length) {
            char data = chars[pos];

            // operation found
            if (data < '0' || data > '9') {
                // store number
                if (sb.length() > 0) {
                    items.add(sb.toString());
                    sb = new StringBuilder();
                }

                items.add(new String(new char[] { data }));

            } else {
                sb.append(data);
            }

            ++pos;
        }

        if (sb.length() > 0)
            items.add(sb.toString());

        return items;
    }

    /**
     *
     * @param items
     * @return
     */
    private Stack getReversePolishNotation(List<String> items) {
        Stack rpnStack = new Stack();
        Stack operationStack = new Stack();

        for (String item : items) {
            Operation operation = Operation.newOperation(item);

            // if item is number
            if (operation == null) {
                rpnStack.push(Integer.parseInt(item));

            } else {
                if (operation.getOp() == Operation.LEFT_BRACE) { // for (
                    operationStack.push(operation);

                } else if (operation.getOp() == Operation.RIGHT_BRACE) { // for )
                    Operation topOp = (Operation) operationStack.pop();

                    while (topOp.getOp() != Operation.LEFT_BRACE) {
                        rpnStack.push(topOp);

                        if (operationStack.size() > 0)
                            topOp = (Operation) operationStack.pop();
                    }

                } else {
                    while (true) {
                        //
                        if (operationStack.size() == 0) {
                            operationStack.push(operation);
                            break;
                        }

                        Operation topOp = (Operation) operationStack.peek();
                        if (topOp.getOp() == Operation.LEFT_BRACE) {
                            operationStack.push(operation);
                            break;
                        }

                        //
                        if (Operation.isPrior(operation, topOp)) {
                            operationStack.push(operation);
                            break;
                        }

                        //
                        topOp = (Operation) operationStack.pop();
                        rpnStack.push(topOp);
                    }
                }

            }
        }

        //
        while (operationStack.size() > 0) {
            rpnStack.push(operationStack.pop());
        }

        return rpnStack;
    }

    /**
     *
     * @param statement
     * @return
     */
    public int calculate(String statement) {
        List<String> items = parseStatement(statement);
        Stack rpnStack = getReversePolishNotation(items);

        //
        Stack<Integer> paramStack = new Stack<>();
        for (int i = 0; i < rpnStack.size(); i++) {
            Object item = rpnStack.get(i);

            if (item instanceof Integer) {
                paramStack.push((Integer) item);

            } else {
                Operation operation = (Operation) item;
                int right = paramStack.pop();
                int left = paramStack.pop();

                if (operation.getOp() == Operation.ADD)
                    paramStack.push(left + right);
                else if (operation.getOp() == Operation.SUB)
                    paramStack.push(left - right);
                else if (operation.getOp() == Operation.MUL)
                    paramStack.push(left * right);
                else if (operation.getOp() == Operation.DIV)
                    paramStack.push(left / right);
            }
        }

        return paramStack.peek();
    }


    //===============================================================
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int value = calculator.calculate("1 + 2 * 4 +(3-2*5)");
        int n = 0;
    }
}
