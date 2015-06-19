package tests;

import static org.junit.Assert.*;


import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.CallGraphBuilderCancelException;
import com.ibm.wala.ipa.cha.ClassHierarchyException;

import util.CGUtils;
import util.StringGraph;

/**
 * This class performs detailed tests
 * */
public class DetailsTests {
	
	/*Tests using SampleOne*/
	//samples.SampleOne.uncalledMethod()V is never called
	@Test
	public void uncalledMethodExistenceTest(){	

		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_ONE);
			graph = new StringGraph(cg);
			assertFalse(graph.nodes.containsKey("samples.SampleOne.uncalledMethod()V"));
		} catch (Exception e) { fail(e.getMessage()); }			
	}
	
	@Test
	public void uncalledMethodInEdgeTest(){	
		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_ONE);
			graph = new StringGraph(cg);
			for(String node : graph.nodes.keySet() ){
				assertFalse(graph.nodes.get(node).contains("samples.SampleOne.uncalledMethod()V"));
			}
		} catch (Exception e) { fail(e.getMessage()); }			
	}

	
	/* Tests using SampleTwo  */
	@Test
	public void initializersTest(){
		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_TWO);
			graph = new StringGraph(cg);
			ArrayList<String> edges;
			edges = graph.nodes.get("samples.SampleTwo.main([Ljava/lang/String;)V");
			assertTrue(edges.contains("samples.SampleTwo.<init>()V"));
			
			edges = graph.nodes.get("samples.SampleTwo.<init>()V");
			assertTrue(edges.isEmpty());			
			
		} catch (Exception e) { fail(e.getMessage()); }		
		
	}
	
	@Test
	public void mutualRecursionTest() {
		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_TWO);
			graph = new StringGraph(cg);
			ArrayList<String> edges = graph.nodes.get("samples.SampleTwo.GreatestCommonDivisor(II)I");
			assertTrue(edges.contains("samples.SampleTwo.GreatestCommonDivisor(II)I"));
		} catch (Exception e) { fail(e.getMessage()); }
	}
	
	@Test
	public void indirectRecursionTest(){
		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_TWO);
			graph = new StringGraph(cg);
			ArrayList<String> edges;
			edges = graph.nodes.get("samples.SampleTwo.CallMeA(Ljava/lang/String;)Ljava/lang/String;");
			assertTrue(edges.contains("samples.SampleTwo.CallMeB(Ljava/lang/String;)Ljava/lang/String;"));

			edges = graph.nodes.get("samples.SampleTwo.CallMeB(Ljava/lang/String;)Ljava/lang/String;");
			assertTrue(edges.contains("samples.SampleTwo.CallMeC(Ljava/lang/String;)Ljava/lang/String;"));
			
			edges = graph.nodes.get("samples.SampleTwo.CallMeC(Ljava/lang/String;)Ljava/lang/String;");
			assertTrue(edges.contains("samples.SampleTwo.CallMeA(Ljava/lang/String;)Ljava/lang/String;"));
						
		} catch (Exception e) { fail(e.getMessage()); }			
	}
	
	
	/* Tests using SampleThree  */	
	
	@Test
	public void inheritanceMethodEdge(){
		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_THREE);
			graph = new StringGraph(cg);
			ArrayList<String> edges;	
			edges = graph.nodes.get("samples.SampleThree.getNumber()I");
			assertTrue(edges.contains("samples.BasicClass.getNumber()I")); // parent class
		
			edges = graph.nodes.get("samples.BasicClass.getNumber()I");
			assertFalse(edges.contains("samples.SampleThree.getNumber()I"));
			
		} catch (Exception e) { fail(e.getMessage()); }			
	}	


}
