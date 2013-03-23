package antlr.tests;

import static org.junit.Assert.*;
import main.FineFunctionParser.FineFunctionParser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

public class FineFunctionParserTest {

	@Test
	public void testIf()
	{
		String input = "if(foo){}";
		FineFunctionParser functionParser = new FineFunctionParser();
		ParseTree tree = functionParser.parseString(input);
		String output = tree.toStringTree(functionParser.parser);
		System.out.println(output);
		assertTrue(output.contains("(selection_or_iteration if"));
	}

	@Test
	public void testStructInFunc()
	{
		String input = "class foo{ int x; };";
		FineFunctionParser functionParser = new FineFunctionParser();
		ParseTree tree = functionParser.parseString(input);
		String output = tree.toStringTree(functionParser.parser);
		assertTrue(output.contains("class_def"));
	}
	
	@Test
	public void testFunctionCall()
	{
		String input = "foo(x);";
		FineFunctionParser functionParser = new FineFunctionParser();
		ParseTree tree = functionParser.parseString(input);
		String output = tree.toStringTree(functionParser.parser);
		assertTrue(output.contains("function_argument_list"));
	}
	
	@Test
	public void testCallViaPtr()
	{
		String input = "ptr->foo(x);";
		FineFunctionParser functionParser = new FineFunctionParser();
		ParseTree tree = functionParser.parseString(input);
		String output = tree.toStringTree(functionParser.parser);
		assertTrue(output.contains("function_argument_list"));
	}
	
	@Test
	public void testCallWithExprInArg()
	{
		String input = "foo(x == 1, x++);";
		FineFunctionParser functionParser = new FineFunctionParser();
		ParseTree tree = functionParser.parseString(input);
		String output = tree.toStringTree(functionParser.parser);
		assertTrue(output.contains("function_argument_list"));
	}
	
	@Test
	public void testAssignmentExpr()
	{
		String input = "x = y + 1;";
		FineFunctionParser functionParser = new FineFunctionParser();
		ParseTree tree = functionParser.parseString(input);
		String output = tree.toStringTree(functionParser.parser);
		System.out.println(output);
		assertTrue(output.contains("assign_expr"));
	}
	
	@Test
	public void testComplexAssignment()
	{
		String input = "k += ((c = text[k]) >= sBMHCharSetSize) ? patlen : skip[c];";
		FineFunctionParser functionParser = new FineFunctionParser();
		ParseTree tree = functionParser.parseString(input);
		String output = tree.toStringTree(functionParser.parser);
		System.out.println(output);
		assertTrue(output.contains("assign_expr"));
	}
	
	@Test
	public void testComplexFor()
	{
		String input = "for(int k = 0; k < 10; ( k += ((c = text[k]) >= sBMHCharSetSize) ? patlen : skip[c]) ){}";
		FineFunctionParser functionParser = new FineFunctionParser();
		ParseTree tree = functionParser.parseString(input);
		String output = tree.toStringTree(functionParser.parser);
		System.out.println(output);
		assertTrue(output.contains("assign_expr"));
	}
	
}
