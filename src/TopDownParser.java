//Fabian Schurk
public class TopDownParser {

    private String regularExpression; //the expression to parse
    public int pos=0; //position of the current char

    public Visitable parse(String regularExpression){
        this.regularExpression = regularExpression;
        Visitable syntaxTree = null;
        try {
            //try to parse the regular expression
            syntaxTree = start();
        }
        catch (Exception e){
            //otherwise print Exception and return null
            System.out.println(e.getMessage());
        }
        pos=0;//Reset position counter
        return syntaxTree;
    }

    private Visitable start(){
        //Only one Node
        if(regularExpression== "#") return new OperandNode("#");
        //check the following conditions
        //length of regular Expression hast to be >0 otherwise IndexOutOfBoundsException,
        //has to start with ( and end with )#
        else if(regularExpression.length()>=1 && regularExpression.charAt(0)=='('
                && regularExpression.charAt(regularExpression.length()-1)=='#'&& regularExpression.charAt(regularExpression.length()-2)==')'){
            OperandNode leaf = new OperandNode("#");
            pos++;
            return new BinOpNode("°", regExp(null), leaf);
        }
        //otherwise its no regular expression --> throw
        throw new RuntimeException("Exception: This isn't a valid expression!");
    }
    private Visitable regExp(Visitable parent){
        char c = regularExpression.charAt(pos);
        if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
            return re(term(null));}

        throw new RuntimeException("Exception: This isn't a valid expression!");
    }
    private Visitable re(Visitable parent){
        char c=regularExpression.charAt(pos);
        if(c==')'){pos++;
            return parent;}//
        else if(c == '|'){ //add | to tree as BinOpNode
            pos ++;
            return re(new BinOpNode("|",parent,term(null)));
        }
        throw new RuntimeException("Exception: This isn't a valid expression!");
    }
    private Visitable term(Visitable parent){
        char c = regularExpression.charAt(pos);
        if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
            if (parent != null)
            {
                //add ° as BinOpNode
                return term(new BinOpNode("°", parent, factor(null)));
            }
            else
            {
                return term(factor(null));
            }
        }
        else if (c == '|'|| c == ')'){
            return parent;
        }
        throw new RuntimeException("Exception: This isn't a valid expression!");
    }
    private Visitable factor(Visitable parent){
        char c = regularExpression.charAt(pos);
        if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
            return hop(elem(null));
        }
        throw new RuntimeException("Exception: This isn't a valid expression!");
    }
    private Visitable hop(Visitable parent){
        char c = regularExpression.charAt(pos);
        if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '('||c==')'|| c =='|' ){
            return parent;
        }
        //add Operator (*,+,?) to tree(UnaryOpNode)
        else if(c== '*'|| c == '+' || c == '?') {
            pos++;
            return new UnaryOpNode(Character.toString(c), parent);
        }
        throw new RuntimeException("Exception: This isn't a valid expression!");

    }
    private Visitable elem(Visitable parent){
        char c = regularExpression.charAt(pos);
        //add char
        if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'){
            return alphanum(null);
        }
        //go on, don't add ) to tree
        else if(c == '(') {
            pos++;
            return regExp(null);
        }

        throw new RuntimeException("Exception: This isn't a valid expression!");
    }
    private Visitable alphanum(Visitable parent){
        //add char to tree(OperandNode)
        char c = regularExpression.charAt(pos);
        if (c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z') {
            pos++;
            return new OperandNode(Character.toString(c));
        }
        throw new RuntimeException("Exception: This isn't a valid expression!");
    }}

