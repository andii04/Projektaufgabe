public class Parser {

    private String regEx;

private Visitable start(){
    if(regEx== "#") return new OperandNode("#");
    else if(regEx.charAt(0)=='('&& regEx.charAt(regEx.length())=='#'&& regEx.charAt(regEx.length()-1)==')'){
    OperandNode leaf = new OperandNode("#");
    return new BinOpNode("", regExp(null), leaf);
    }
    else {
        return null;
    }
}

private Visitable  regExp(Visitable v){
    return rE(term(null));
}

private Visitable rE(Visitable v){
}

private Visitable term(Visitable v){

}
private Visitable factor(Visitable v){
}
private Visitable hop(Visitable v){
    switch (){
        case '?':
            return  new UnaryOpNode('?', HOp.parameter);
            break;
        case '+':
            return new UnaryOpNode('+', HOp.parameter);

    }
}
private Visitable elem(Visitable v){

}

private Visitable alphanum(Visitable v){

}
}


