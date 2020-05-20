import java.io.File
import java.util.ArrayDeque
import java.util.Queue
import java.util.LinkedList

fun preprocess(expr:String): List<Any>?{
    var replaced = expr.replace(" ", "")
    var tokens = mutableListOf<Any>()
    var numstr:String = ""
    for (r in replaced) {
        if (")(+-*/".contains(r) && !numstr.equals("")){
            try {tokens.add(numstr.toDouble())}
            catch (e: NumberFormatException){return null}
            tokens.add(r.toString())
            numstr = ""
        }
        else if ("()+-*/".contains(r)) tokens.add(r.toString())
        else numstr += r
    }
    if (numstr != ""){
        try {tokens.add(numstr.toDouble())}
        catch (e: NumberFormatException){return null}
    }
    return tokens
}
fun valid(tokens: List<Any>): Boolean{
    var left: Int = 0
    for (t in 0 until tokens.count()){
        when (tokens[t]){
            is Double -> {if ((t-1>=0 && (")".contains(tokens[t-1].toString()) || tokens[t-1] is Double)) || (t+1<tokens.count() && ("(".contains(tokens[t+1].toString()) || tokens[t+1] is Double))) return false}
            "(" -> {
                left++
                if ((t-1>=0 && (")".contains(tokens[t-1].toString()) || tokens[t-1] is Double)) || (t+1<tokens.count() && ")+-*/".contains(tokens[t+1].toString()))) return false
            }
            ")" -> {
                left--
                if ((t-1>=0 && "(+-*/".contains(tokens[t-1].toString())) || (t+1<tokens.count() && ("(".contains(tokens[t+1].toString()) || tokens[t+1] is Double))) return false
            }
            "+", "-", "*", "/" -> {if ((t-1>=0 && "(+-*/".contains(tokens[t-1].toString())) || (t+1<tokens.count() && ")+-*/".contains(tokens[t+1].toString()))) return false}
        }
        if (left < 0) return false
    }
    if (left != 0) return false
    return true
}
fun recur(tokens: List<Any>): Double{
    var output:Queue<Any> = LinkedList<Any>()
    var operator = ArrayDeque<String>()
    var t:Int = 0
    while (t < tokens.count()){
        when (tokens[t]){
            "(" ->{
                var left:Int = 1
                var sub = mutableListOf<Any>()
                while(++t < tokens.count()){
                    if (tokens[t] == "(") left++
                    else if(tokens[t] == ")") left--
                    if(left == 0) break
                    sub.add(tokens[t])
                }
                output.add(recur(sub))
            }
            "+", "-" ->{
                if (operator.isEmpty()) operator.push(tokens[t].toString())
                else {
                    while (!operator.isEmpty()) output.add(operator.pop())
                    operator.push(tokens[t].toString())
                }
            }
            "*", "/" ->{
                if (operator.isEmpty() || "+-".contains(operator.peek())) operator.push(tokens[t].toString())
                else {
                    while (!operator.isEmpty() && !"+-".contains(operator.peek())) output.add(operator.pop())
                    operator.push(tokens[t].toString())
                }
            }
            is Double ->{output.add(tokens[t])}
        }
        t++
    }
    while (!operator.isEmpty()) output.add(operator.pop())
    var compute = ArrayDeque<Double>()
    while (!output.isEmpty()){
        var o = output.remove()
        if (o is Double) compute.push(o)
        else{
            var passive:Double = compute.pop()
            var active:Double = compute.pop()
            when (o){
                "+" ->{compute.push(active + passive)}
                "-" ->{compute.push(active - passive)}
                "*" ->{compute.push(active * passive)}
                "/" ->{compute.push(active / passive)}
            }
        }
    }
    return compute.pop()
}
fun eval(expr: String): Double? {
    val tokens = preprocess(expr)
    if (tokens != null && valid(tokens)) return recur(tokens)
    else return null
}

// DO NOT modify the following code in your demo.
fun main() {
    val inputLines = File("input").readLines()
    val answerLines = File("answer").readLines()
    for ((index, answer) in answerLines.withIndex()) {
        val case = index + 1
        // if you want to skip certain test case,
        // uncomment or modify the following line
        // if (setOf(4,5,6,7,8,9).contains(case)) continue
    	val output = "%.8g".format(eval(inputLines[index]))
        if (output != answer) {
            println("Case $case:\n${inputLines[index]}")
            println("Your code outputs $output")
            println("The answer should be $answer")
            return
        }
    }
    println("Your code passes all test cases.")
}