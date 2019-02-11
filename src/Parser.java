public class Parser {

    private String regEx;
    public int pos=0;

private Visitable start(){
    if(regEx== "#") return new OperandNode("#");
    else if(regEx.charAt(0)=='('&& regEx.charAt(regEx.length())=='#'&& regEx.charAt(regEx.length()-1)==')'){
    OperandNode leaf = new OperandNode("#");
    pos++;
    return new BinOpNode("", regExp(null), leaf);
    }
    else {
        return null;
    }
}

private Visitable  regExp(Visitable p){
    return rE(term(null));
}

private Visitable rE(Visitable p){
}

private Visitable term(Visitable p){
    char c = regEx.charAt(pos);

    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
        factor(null);

        if (p != null)
        {
            return term(new BinOpNode('',
                    p, factor(null));)
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
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'|| c == '(' ){
        return p;
    }
    else if(c== '*'|| c == '+' || c == '?') return new UnaryOpNode(Character.toString(c), p);
    return null;

}
private Visitable elem(Visitable p){
    char c = regEx.charAt(pos);
    if(c>='0'&& c<='9'||c>='a'&& c<='z'||c>='A'&& c<='Z'){
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


