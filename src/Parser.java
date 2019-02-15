public class Parser {

    //private String regularExpression= "((a|b)*abb)#"; Andi's Regexpr.
    private String regularExpression= "(a*b*(a|b)abc)#";
    public int pos=0;
    public Visitable syntaxTree;
    
    public void setRegularExpression(String regularExpression){
        this.regularExpression = regularExpression;
    }
    public Visitable getTree(){
        return syntaxTree;
    }

    public void parse(){
        syntaxTree = start();
    }
    private Visitable start(){
    if(regularExpression== "#") return new OperandNode("#");
    else if(regularExpression.charAt(0)=='('&& regularExpression.charAt(regularExpression.length()-1)=='#'&& regularExpression.charAt(regularExpression.length()-2)==')'){
    OperandNode leaf = new OperandNode("#");
    pos++;
    return new BinOpNode("°", regExp(null), leaf);
    }
        return null;
}
    private Visitable regExp(Visitable p){
    char c = regularExpression.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
    return rE(term(null));}

    return null;
}
    private Visitable rE(Visitable p){
    char c=regularExpression.charAt(pos);
    if(c==')'){pos++;
        return p;}
    else if(c == '|'){
        pos ++;
            return rE(new BinOpNode("|",p,term(null)));
    }
        return null;
}
    private Visitable term(Visitable p){
    char c = regularExpression.charAt(pos);

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
    char c = regularExpression.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
        return hop(elem(null));
    }
    return null;
}
    private Visitable hop(Visitable p){

    char c = regularExpression.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '('||c==')'|| c =='|' ){
        return p;
    }
    else if(c== '*'|| c == '+' || c == '?') { pos++; return new UnaryOpNode(Character.toString(c), p);}
    return null;

}
    private Visitable elem(Visitable p){
    char c = regularExpression.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'){
        return alphanum(null);
    }
    else if(c == '(') {pos++; return regExp(null);}

    return null;
}
    private Visitable alphanum(Visitable p){
    char c = regularExpression.charAt(pos);
    if (c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z') {pos++; return new OperandNode(Character.toString(c));}
    return null;
}}



