package br.edu.ifpe.viewprojects.Entites;

public class Project {
    private Integer id;
    private String name;
    private String description;
    private String status;
    private String startDate;
    private String endDate;
    private Integer userId;

    public Project(Build build) {
        this.name = build.name;
        this.description = build.description;
        this.status = build.status;
        this.startDate = build.startDate;
        this.endDate = build.endDate;
        this.userId = build.userId;
        this.id = build.id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public class Build {
        private Integer id;
        private String name;
        private String description;
        private String status;
        private String startDate;
        private String endDate;
        private Integer userId;

        public Build setId(Integer id) {
            this.id = id;
            return this;
        }

        public Build setName(String name) {
            this.name = name;
            return this;
        }

        public Build setDescription(String description) {
            this.description = description;
            return this;
        }

        public Build setStatus(String status) {
            this.status = status;
            return this;
        }

        public Build setStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Build setEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public Build setUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }
}
