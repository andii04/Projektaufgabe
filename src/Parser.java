public class Parser {

    private String regEx;
    public int pos=0;
    public Visitable syntaxTree;
    public void go(){
        regEx = "(a|b)#";
        syntaxTree= start();
        System.out.println(syntaxTree);
    }

private Visitable start(){
    if(regEx== "#") return new OperandNode("#");
    else if(regEx.charAt(0)=='('&& regEx.charAt(regEx.length()-1)=='#'&& regEx.charAt(regEx.length()-2)==')'){
    OperandNode leaf = new OperandNode("#");
    System.out.println("starts");
    pos++;
    return new BinOpNode("", regExp(null), leaf);
    }
    else {
        return null;
    }
}
private Visitable regExp(Visitable p){
    char c = regEx.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
        pos++;
    return rE(term(null));}

    return null;
}
private Visitable rE(Visitable p){
    char c=regEx.charAt(pos);
    if(c==')'){return p;}
    else if(c == '|'){
        pos ++;
            return rE(new BinOpNode("|",p,term(null)));
    }
        return null;
}
private Visitable term(Visitable p){
    char c = regEx.charAt(pos);

    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
        pos++;
        if (p != null)
        {
            return term(new BinOpNode("",
                    p, factor(null)));
        }
        else
        {
            return term(factor(null));
        }
    }
    else if (c == '|'|| c == ')'){
        return p;
    }
    return null;
}
private Visitable factor(Visitable p){
    char c = regEx.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
        pos++;
        return hop(elem(null));
    }
    return null;
}
private Visitable hop(Visitable p){

    char c = regEx.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
        return p;
    }
    else if(c== '*'|| c == '+' || c == '?') return new UnaryOpNode(Character.toString(c), p);
    return null;

}
private Visitable elem(Visitable p){
    char c = regEx.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'){
        pos++;
        return alphanum(null);
    }
    else if(c == '(') return regExp(null);

    return null;
}
private Visitable alphanum(Visitable p){
    char c = regEx.charAt(pos);
    if (c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z') new OperandNode(Character.toString(c));
    return null;
}
}


