/*

Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.

Example:
Input: 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
Output:
[null,[],null,null,["a"],"hello"]
Explanation:
filesystem
Note:
You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
*/
public class FileSystem {
		static abstract class Item {
		public String name;

		public Item(String n) {
			name = n;
		}
	}

	static class File extends Item {
        StringBuilder content;
        public File(String n) {
        	super(n);
        	content = new StringBuilder();
        }
        void append(String newS) {content.append(newS);	}
        String getFile() {
        	return content.toString();
        }
    }
    
    static class Directory extends Item {
        public Map<String, Item> table ;
        public Directory(String n) {
        	super(n);
        	table = new HashMap<>();
        }
    }
    
    Directory root;
    public FileSystem() {
    	root = new Directory("/");
    }
    
    public List<String> ls(String path) {
    	String [] dirs = getList(path);
        Item p = root;
        List<String> ret = new ArrayList<>();
        for(int i = 0; i< dirs.length; ++i) {
        	String d = dirs[i];
        	if(p instanceof File) {
        		return ret;
        	} else {
        		p = ((Directory)p).table.get(d);
        		if(p == null) {
        			return ret;
        		}
        	}
        }
        
        if(p instanceof File) {
        	ret.add(p.name);
        	return ret;
        }
        ret.addAll(((Directory)p).table.keySet());
        Collections.sort(ret);
        return ret;
    }
    
    public void mkdir(String path) {
    	 String [] dirs = getList(path);
         Directory p = root;
         for(String d : dirs) {
        	 Item next = p.table.get(d);
        	 if(next == null) {
        		 Directory n = new Directory(d);
        		 p.table.put(d, n);
        		 p = n;
        	 } else if (next instanceof Directory) {
        		 p = (Directory)next;
        	 } else {
        		 return ;
        	 }
         }
    }
    String [] getList(String path) {
    	if(path == null || path.length() == 0)	return new String[0];
    	if(path.charAt(path.length() - 1) == '/')	path = path.substring(0, path.length() - 1);
    	if(path.length() > 0 && path.charAt(0) == '/') path = path.substring(1);
    	if(path.length() == 0)	return new String[0];
    	String[] dirs = path.split("/");
    	return dirs;
    }
    
    File getTargetFile(String path) {
    	 String [] dirs = getList(path);
         Directory p = root;
         for(int i = 0;i < dirs.length; ++i) {
        	 String d = dirs[i];
        	 Item next = p.table.get(d);
        	 if(next == null) {
        		 if(i == dirs.length - 1) {
        			 File t = new File(d);
        			 p.table.put(d, t);
        			 return t;
        		 } else {
        			 Directory t = new Directory(d);
        			 p.table.put(d, t);
        			 p = t;
        		 }
        	 } else if (next instanceof Directory) {
        		 p = (Directory)next;
        	 } else {
        		 if(i == dirs.length - 1) return (File)next;
        		 return null;
        	 }
         }
         return null;
    }
    
    public void addContentToFile(String filePath, String content) {
        File t = getTargetFile(filePath);
        if(t == null)	return ;
        t.append(content);
    }
    
    public String readContentFromFile(String filePath) {
    	File t = getTargetFile(filePath);
    	if(t == null)	return null;
    	return t.getFile();
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
