package com.example.javaprojectfophone.pojo;

import java.util.Arrays;
import java.util.Objects;

public class PlanBlock {
    private String theme;
    private String[] plans;

    public PlanBlock(String theme, String[] plans) {
        this.theme = theme;
        this.plans = plans;
    }

    public String getTheme() {
        return theme;
    }

    public String[] getPlans() {
        return plans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanBlock planBlock = (PlanBlock) o;
        return theme.equals(planBlock.theme) &&
                Arrays.equals(plans, planBlock.plans);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(theme);
        result = 31 * result + Arrays.hashCode(plans);
        return result;
    }
}
