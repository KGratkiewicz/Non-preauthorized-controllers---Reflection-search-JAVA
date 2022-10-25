
import org.reflections.Reflections;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ControllerListener {
  public static void list(){
    Reflections reflections = new Reflections("package");

    Set<Class<? extends ControllerParent>> allClasses = reflections.getSubTypesOf(ControllerParent.class);

    for (var clas: allClasses) {
      var methods = clas.getMethods();
      for (var method:methods) {
        var adnotations = method.getAnnotations();
        boolean flag = true;
        for (var adnotation:adnotations) {
          if(adnotation.annotationType() == PreAuthorize.class) {
            flag = false;
          }
        }
        List<String> javaMethods = Arrays.asList("wait","equals","toString","hashCode", "getClass", "notify", "notifyAll");

        if(flag && !javaMethods.contains(method.getName()))
        {
          System.out.println(clas.getName() + " " + method.getName());
        }
      }
    }

  }

}
