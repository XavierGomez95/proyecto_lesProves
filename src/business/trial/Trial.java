package business.trial;


public abstract class Trial {
    protected String name;

    public Trial(String name) {
        this.name = name;
    }

    /**
     * @param name String name to be compared with the name of the current Trial.
     * @return true (if the name received by parameter is the same as the trial name) or false (if is not the same).
     */
    public boolean checkName(String name) {
        return name.equals(this.name);
    }

    /**
     * @param trialsName to compare
     * @return true if the nae is equals to the param
     */
    public boolean isNameEqual(String trialsName) {
        return name.equals(trialsName);
    }

    /**
     * Getter
     *
     * @return the name of the trial
     */
    public String getName() {
        return name;
    }


    /**
     * Abstract method statement
     *
     * @return a String representing the trial type.
     */
    public abstract String getType();

    /**
     * Abstract method.
     *
     * @return a String with different information depending on the type of Trial.
     */
    public abstract String listInfo();

    /**
     * @return the name of a trial. Method is overwritten in subclasses used to write in csv files.
     */
    public String getInfo() {
        return name;
    }
}
