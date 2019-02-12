public class Parser {

    private String regEx;
    public int pos=0;
    public Visitable syntaxTree;
    public Visitable go(){
        regEx = "(a*b*(a|b)abc)#";
        return start();
    }

private Visitable start(){
    if(regEx== "#") return new OperandNode("#");
    else if(regEx.charAt(0)=='('&& regEx.charAt(regEx.length()-1)=='#'&& regEx.charAt(regEx.length()-2)==')'){
    OperandNode leaf = new OperandNode("#");
    System.out.println("starts");
    pos++;
    return new BinOpNode("°", regExp(null), leaf);
    }
        return null;
}
private Visitable regExp(Visitable p){
    char c = regEx.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
    return rE(term(null));}

    return null;
}
private Visitable rE(Visitable p){
    char c=regEx.charAt(pos);
    if(c==')'){pos++;
        return p;}
    else if(c == '|'){
        pos ++;
            return rE(new BinOpNode("|",p,term(null)));
    }
        return null;
}
private Visitable term(Visitable p){
    char c = regEx.charAt(pos);

    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
        if (p != null)
        {
            return term(new BinOpNode("°",
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
        return hop(elem(null));
    }
    return null;
}
private Visitable hop(Visitable p){

    char c = regEx.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '('||c==')'|| c =='|' ){
        return p;
    }
    else if(c== '*'|| c == '+' || c == '?') { pos++; return new UnaryOpNode(Character.toString(c), p);}
    return null;

}
private Visitable elem(Visitable p){
    char c = regEx.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'){
        return alphanum(null);
    }
    else if(c == '(') {pos++; return regExp(null);}

    return null;
}
private Visitable alphanum(Visitable p){
    char c = regEx.charAt(pos);
    if (c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z') {pos++; return new OperandNode(Character.toString(c));}
    return null;
}
}



