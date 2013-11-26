package algorithm;

import java.util.Stack;

public class Calculator {

	private char[] cs;
	private Stack<String> num = new Stack<String>();
	private Stack<Operator> op = new Stack<Operator>();
	
	public Calculator (String s) {
		this.cs = (s + "#").toCharArray();
		op.push(new Operator('#'));
	}
	
	public String calculate() {
		StringBuilder numV = new StringBuilder();
		int i = 0;
		while (cs[i] != '#' || op.peek().getOp() != '#') {
			char c = cs[i];
			if (Operator.isOp(c)) {
				if (numV.length() != 0) {
					num.push(numV.toString());
					numV.delete(0, numV.length());
				}
				Operator op2 = new Operator(c);
				switch (op.peek().compare(op2)) {
					case 0 :
						op.pop();
						break;
					case -1 :
						op.push(op2);
						break;
					case 1 :
						String num2 = num.pop();
						String num1 = num.pop();
						num.push(op.pop().calculate(num1, num2));
						continue;
				}
			} else {
				numV.append(c);
			}
			if (c != '#') {
				i++;
			}
		}
		return num.pop();
	}
}
