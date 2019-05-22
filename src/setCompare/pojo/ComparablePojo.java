package setCompare.pojo;

public class ComparablePojo implements Comparable {

    private Integer id;
    private String name;
    private Integer age;

    public ComparablePojo() {
    }

    public ComparablePojo(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

    /**
     * 使用age排序
     *
     * @param obj
     * @return
     */
    @Override
    public int compareTo(Object obj) {
        if (this == obj)
            return 0;
        if (obj == null)
            return 1;
        if (getClass() != obj.getClass())
            return 1;

        ComparablePojo other = (ComparablePojo) obj;

        if (age == null) {
            if (other.age != null)
                return -1;
            else
                return 0;
        } else if (age == other.age) {
            return 0;
        } else if (age > other.age) {
            return 1;
        } else {
            return -1;
        }
    }
}
