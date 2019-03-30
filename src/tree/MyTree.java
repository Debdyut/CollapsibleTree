package tree;

import java.util.*;

public class MyTree {

	public static List<Object> removeDuplicates(List<String[]> l) {
	    Set<String[]> s = new TreeSet<String[]>(new Comparator<String[]>() {

	        @Override
	        public int compare(String[] o1, String[] o2) {	            
	            return Arrays.equals(o1, o2)?0:1;
	        }
	    });
        
	    s.addAll(l);	    
	    
	    List<Object> res = Arrays.asList(s.toArray()); 
	   	
	    return res;
	}
	
	public static String[][] slice2DArray(String[][] o, int start, int end) {            

        List<String[]> data = new ArrayList<>();
        int c = 0;
        for (int i = 0; i < o.length; i++) {
              try {
                    data.add(Arrays.copyOfRange(o[i], start, end));
              }
              catch(Exception e) {}
        }
        
        List<String[]> tmp = new ArrayList<>();
        
        for(String[] sarr : data) {
        	if(Arrays.asList(sarr).contains(null)) {
        		tmp.add(sarr);
        	}
        }
        
        data.removeAll(tmp);
        
        String[][] result = new String[removeDuplicates(data).size()][];
        return removeDuplicates(data).toArray(result);
       
	}	
	
	public static String createTree() {
		
		Scanner sc = new Scanner(System.in);
        
		String[] uinput = { 
			"Car.Sedan.Color",
			"Car.Sedan.Color.Blue",
			"Car.Sedan.Model.Color.Red",
			"Car.Sedan.Model.Color.Blue",		
			"Car.SUV.Color",
			"Car.Hatchback.Color",
			"Car.Hatchback.Model"
		};

	        
        int n = uinput.length;
        
        String[][] inputs = new String[n][];
        
        for (int i = 0; i < n; i++) {                          
              inputs[i] = uinput[i].split("[.]");
        }
        
        Node root = new Node(inputs[0][0]);
                    
        System.out.println(root.getLabel());
        
        int maxLength = 0;
        for(String[] arr: inputs) {
              if(maxLength < arr.length) {
                    maxLength = arr.length;
              }
        }
        
        System.out.println("Maxlength: " + maxLength);
                  
        
        for(int i = 0; i < maxLength - 1; i++) {
			String[][] prev = slice2DArray(inputs, 0, i+1);
						
			for(String[] s : prev) {
				System.out.println("Parent: " + Arrays.toString(s));				
				Set<String> childList = new TreeSet<>();
				for(String[] x : inputs) {					
					if(Arrays.equals(Arrays.copyOfRange(x, 0, i+1),s)) {
						try {
							childList.add(x[i+1]);
						}
						catch(Exception e) {}
					}
				}
				System.out.println("Children: " + childList);
				
				Node current = root;				
				
				for(int k = 1; k < s.length; k++) {
					current = current.getChildByLabel(s[k]);
				}
				
				for(String childLabel : childList) {
					if(current.getChildByLabel(childLabel) == null) {
						current.addChild(new Node(childLabel));
					}
				}
				
			}
		}   
        
//        {
//            text: { name: "Parent node" },
//            children: [
//                {
//                    text: { name: "First child" }
//                },
//                {
//                    text: { name: "Second child" }
//                }
//            ]
//        }       

        return getJson(root);
        
	}
	
	public static String getJson(Node current) {
		String res = "{ \"text\": { \"name\": \"" + current.label + "\" }";
		
		if(current.getChildList().size() > 0) {
			res += ", \"children\": [";
			
			for(int i = 0; i < current.childList.size(); i++) {
				res += getJson(current.childList.get(i));
				if(i != current.childList.size() - 1) {
					res += ",";
				}
			}
			
			res += "]";
		}
		
		res += "}";
		return res;
	}
	
	
}

