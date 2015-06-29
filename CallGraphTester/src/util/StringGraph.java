package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import com.ibm.wala.classLoader.CallSiteReference;
import com.ibm.wala.ide.util.EclipseProjectPath;
import com.ibm.wala.ide.util.HeadlessUtil.EclipseCompiler;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.Context;

public class StringGraph {
	public LinkedHashMap<String, ArrayList<String>> nodes;

	/*we didn't put these prefix into exclusion files because we are  pruning our graph*/
	private static boolean valid(String sig){
		return !( (sig.startsWith("java.") || sig.startsWith("com.ibm") || sig.startsWith("javax.") || sig.startsWith("java.lang.StringBuilder.") ));
		
	} 
	
	public StringGraph(CallGraph cg){
		this.nodes = new LinkedHashMap<String, ArrayList<String>>();
		this.fromCG(cg);
	}
	
	private void fromCG(CallGraph cg){
		Iterator<CGNode> it = cg.iterator();
		while(it.hasNext()){
			CGNode node = it.next();
			String sig = node.getMethod().getSignature();
			if(!valid(sig) ) continue;
			ArrayList<String> arr = new ArrayList<String>();
			Iterator<CallSiteReference> it2 = node.iterateCallSites();
			while(it2.hasNext()){
				CallSiteReference ref = it2.next();
				String adjSig = ref.getDeclaredTarget().getSignature(); 
				if(!valid(adjSig)) continue;
				arr.add(adjSig);
			}
			this.nodes.put(sig, arr);
		}		
	}
	
	@Override
	public String toString() {
		String str = "";
		Set<String> set = this.nodes.keySet();
		for(String no : set ){
			str += "[Node: " + no + "]\n    ";
			ArrayList<String> edges = this.nodes.get(no);
			if(edges.isEmpty()) str += "Has no output edges";
			else {
				str+= "Edges to:";
				for(String e : edges){
					str += " [" + e + "]";
				}
			}
			str += "\n";
		}
		
		
		return str;
	}
	
	
}
