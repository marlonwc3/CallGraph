package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import util.CGUtils;
import util.StringGraph;

import com.ibm.wala.ipa.callgraph.CallGraph;

/**
 * This Class performs a basic sanity test on sample
 * */
public class SanityTests {
	

	@Test
	public void SampleOneBasicSanity() {
		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_ONE);
			graph = new StringGraph(cg);
			assertTrue(graph.nodes.containsKey("samples.SampleOne.main([Ljava/lang/String;)V"));
			assertTrue(graph.nodes.containsKey("samples.SampleOne.fooA(I)V"));
			assertTrue(graph.nodes.containsKey("samples.SampleOne.fooB(I)V"));
			
			ArrayList<String> edges;
			// main 			
			edges = graph.nodes.get("samples.SampleOne.main([Ljava/lang/String;)V");
			assertFalse(edges.isEmpty());
			assertTrue(edges.contains("samples.SampleOne.fooA(I)V"));
			// fooA
			edges = graph.nodes.get("samples.SampleOne.fooA(I)V");
			assertFalse(edges.isEmpty());
			assertTrue(edges.contains("samples.SampleOne.fooB(I)V"));
			// fooB
			edges = graph.nodes.get("samples.SampleOne.fooB(I)V");
			assertTrue(edges.isEmpty());
			
		} catch (Exception e) { fail(e.getMessage()); }
	}
	
	@Test
	public void SampleTwoSanity() {
		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_TWO);
			graph = new StringGraph(cg);
			assertTrue(graph.nodes.containsKey("samples.SampleTwo.main([Ljava/lang/String;)V"));
			assertTrue(graph.nodes.containsKey("samples.SampleTwo.<init>()V"));
			assertTrue(graph.nodes.containsKey("samples.SampleTwo.GreatestCommonDivisor(II)I"));
			assertTrue(graph.nodes.containsKey("samples.SampleTwo.CallMeA(Ljava/lang/String;)Ljava/lang/String;"));
			assertTrue(graph.nodes.containsKey("samples.SampleTwo.CallMeB(Ljava/lang/String;)Ljava/lang/String;"));
			assertTrue(graph.nodes.containsKey("samples.SampleTwo.CallMeC(Ljava/lang/String;)Ljava/lang/String;"));
		} catch (Exception e) { fail(e.getMessage()); }
	}
	
	
	@Test
	public void SampleThreeSanity() {
		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_THREE);
			graph = new StringGraph(cg);
			assertTrue(graph.nodes.containsKey("samples.SampleThree.main([Ljava/lang/String;)V"));
			assertTrue(graph.nodes.containsKey("samples.SampleThree.<init>()V"));
			assertTrue(graph.nodes.containsKey("samples.SampleThree.getNumber()I"));
			assertTrue(graph.nodes.containsKey("samples.BasicClass.<init>()V"));
			assertTrue(graph.nodes.containsKey("samples.BasicClass.getNumber()I"));
		} catch (Exception e) { fail(e.getMessage()); }
	}	
	
	
	
	
	
	
	


}
