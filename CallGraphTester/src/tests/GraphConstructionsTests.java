package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import util.CGUtils;
import util.StringGraph;

import com.ibm.wala.ipa.callgraph.CallGraph;


/**
 * This class perform test up graph construction using WALA and the StringGraph representation
 * */
public class GraphConstructionsTests {


	@Test
	public void SampleOneConstruction(){
		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_ONE);
			graph = new StringGraph(cg);
			assertTrue(cg != null); 
			assertTrue(graph != null);
		} catch (Exception e) {fail(e.getMessage()); }	
	}
	
	@Test
	public void SampleTwoConstruction() {
		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_TWO);
			graph = new StringGraph(cg);
			assertTrue(graph.nodes.keySet().size() > 0 );
			assertTrue(graph.nodes.values().size() > 0 );
		} catch (Exception e) { fail(e.getMessage()); }
	}
	
	@Test
	public void SampleThreeConstruction() {
		try {
			CallGraph cg;
			StringGraph graph;			
			cg = CGUtils.buildCG(SamplePaths.SAMPLE_THREE);
			graph = new StringGraph(cg);
			assertTrue(graph.nodes.keySet().size() > 0 );
			assertTrue(graph.nodes.values().size() > 0 );
		} catch (Exception e) { fail(e.getMessage()); }
	}
		
	

}
