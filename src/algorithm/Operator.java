package algorithm;

public class Operator {

	private char op;
	public Operator (char op) {
		this.op = op;
	}
	
	public char getOp() {
		return this.op;
	}
	
	public String calculate(String num1, String num2) {
		switch (this.op) {
		case '+' :
			return String.valueOf((Integer.valueOf(num1) + Integer.valueOf(num2)));
		case '-' :
			return String.valueOf((Integer.valueOf(num1) - Integer.valueOf(num2)));
		case '*' :
			return String.valueOf((Integer.valueOf(num1) * Integer.valueOf(num2)));
		case '/' :
			return String.valueOf((Integer.valueOf(num1) / Integer.valueOf(num2)));
		default :
			return "";
		}
	}
	
	public static boolean isOp(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '#';
	}

	public int compare(Operator op2) {
		char c1 = this.op;
		char c2 = op2.getOp();
        if (c2 == '#') {
            return 1;
        }
        if (c1 == '#') {
            if (c2 == '#') return 0;
            return -1;
        } else if (c1 == '(') {
            if (c2 == ')') return 0;
            return -1;
        } else if (c1 == ')') {
            if (c2 == '(') return -1;
            return 1;
        }else if (c1 == '+' || c1 == '-') {
            if (c2 == '*' || c2 == '/' || c2 == '(') {
                return -1;
            }
            return 1;
        } else if (c1 == '*' || c1 == '/') {
            if (c2 == '(') return -1;
            return 1;
        }
        return 0;
	}
}
