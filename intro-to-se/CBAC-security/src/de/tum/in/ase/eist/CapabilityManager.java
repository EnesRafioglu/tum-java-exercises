package de.tum.in.ase.eist;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CapabilityManager {

   private final HashMap<Application,Set<Capability>> capabilityMap;

    public CapabilityManager(HashMap<Application, Set<Capability>> capabilityMap) {
        this.capabilityMap = capabilityMap;
    }

    /*
    TODO 2.3.1
        Adjust the implementation of the method grantCapability(Application, Capability),
        that it adds a capability of an application, to the capabilityMap, if it does not exist yet.
     */
    public void grantCapability(Application application, Capability capability) {


        capabilityMap.computeIfAbsent(application, k -> new HashSet<>()).add(capability);

    }

    /*
    TODO 2.3.2
        Adjust the implementation of the method revokeCapability(Application, Capability),
        that it removes a capability of an application of the capabilityMap, if it exists.
     */
    public void revokeCapability(Application application, Capability capability) {
        if(capabilityMap.get(application) != null){
            capabilityMap.get(application).remove(capability);
        }
    }

    /*
    TODO 2.2
        Adjust the method hasCapability(Application, File, Permission), which is
        supposed to check, if a requested capability exits in the capabilityMap.
        A capability is the permission of an application for a certain file.
        Hint: Check the Capability class for useful methods.
     */
    public boolean hasCapability(Application application, File file, Permission permission) {
       Set<Permission> p = new HashSet<>();
       p.add(permission);
       Capability capability = new Capability(file,p);
       Set<Capability> set = capabilityMap.get(application);
       return set != null && set.contains(capability);
    }

    /*
    TODO 2.3.3
        Adjust the implementation of the method delegateCapability(Application,
        Application, Capability), that it revokes a certain capability from the
        "old" application and grants it for the "new" application.
     */
    public void  delegateCapability(Application oldApplication, Application newApplication, Capability capability) {
        revokeCapability(oldApplication,capability);
        grantCapability(newApplication,capability);
    }

}
