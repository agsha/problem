package problem;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger log = LogManager.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public Map getMap(String[] lines) {
        Map<String, String> map =  new HashMap<String, String>();
        Map<String, List<String>> dependency = new HashMap<String, List<String>>();
        String currentJson = null ;
        String currentRef = null;
        Pattern p = Pattern.compile("!\\w+");

        for(int i=0; i<lines.length; i++) {
            String line = lines[i];
            if(line.trim().startsWith("!")) {

                if(currentRef!=null) {
                   map.put(currentRef, currentJson);
                }
                String[] split = line.trim().split(",");
                currentJson = "";
                currentRef = split[1];
                dependency.put(currentRef, new ArrayList<String>());
                continue;
            }
            Matcher m = p.matcher(line);
            List<String> dependencies = dependency.get(currentRef);
            while(m.find()) {
                dependencies.add(m.group().substring(1));
            }
            currentJson+=(line);
        }
        map.put(currentRef, currentJson);

        //log.debug(dependency);
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        List<String> topologicalSort = new ArrayList<String>();
        for (String key : dependency.keySet()) {
            dfs(key, visited, topologicalSort, dependency);
        }

        log.debug(topologicalSort);
        Map<String, String> finalMap = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String ref = entry.getKey();
            String json = entry.getValue();
            while(true) {
                log.debug(ref+", "+json);

                Matcher m = p.matcher(json);
                if(!m.find()) break;
                String jsonToReplace = map.get(m.group().substring(1));
                json = json.substring(0, m.start())+jsonToReplace+json.substring(m.end());
            }
            finalMap.put(ref, json);
        }
        return finalMap;
    }

    private void dfs(String key, Map<String, Boolean> visited, List<String> topologicalSort, Map<String, List<String>> dependency) {
        if(visited.containsKey(key)&&visited.get(key)) return;
        visited.put(key, true);
        for (String s : dependency.get(key)) {
            dfs(s, visited, topologicalSort, dependency);
        }
        topologicalSort.add(key);
    }
}
