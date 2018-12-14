package y2018.day7;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
class Step implements Comparable{

    String name;
    Set<Step> dependents;
    Set<Step> prereqs = new TreeSet<>();

    Step(String name) {
        this.name = name;
    }

    public void addDependent(Step dependent) {
        if(this.dependents == null) this.dependents = new TreeSet<>();

        this.dependents.add(dependent);
    }

    public void addPrerequisite(Step prereq) {
        this.prereqs.add(prereq);
    }

    @Override
    public int compareTo(Object o) {

        return this.name.compareTo(((Step)o).name);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (prereqs != null) {
            for (Step prereq : prereqs) {
                sb.append(prereq.name).append(" ");
            }
        }

        sb.append("<- ").append(name).append(" -> ");

        if (dependents != null) {
            for (Step child : dependents) {
                sb.append(child.name).append(" ");
            }
        }

        return sb.toString();

    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Step)obj).name);
    }
}
