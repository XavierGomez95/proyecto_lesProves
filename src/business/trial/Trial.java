package business.trial;


public abstract class Trial {
    protected String name;

    public Trial(String name) {
        this.name = name;
    }

    /**
     *
     * @return the name of a trial. Method is overwritten in subclasses.
     */
    public String getInfo() {
        return name;
    }

    /**
     * Abstract method.
     * @return a String with different information depending on the type of Trial.
     */
    public abstract String listInfo();

    /**
     * Getter
     * @return the name of the trial
     */
    public String getName() {
        return name;
    }


    public boolean isNameEqual(String trialsName) {
        return name.equals(trialsName);
    }


    /**
     *
     * @param name String name to be compared with the name of the current Trial.
     * @return true (if the name received by parameter is the same as the trial name) or false (if is not the same).
     */
    public boolean checkName(String name) {
        return name.equals(this.name);
    }

    /**
     * Abstract method statement
     * @return a String representing the trial type.
     */
    public abstract String getType();
}
