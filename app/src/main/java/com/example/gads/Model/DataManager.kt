package com.example.gads.Model

object DataManager {
    val skills = arrayListOf<skill>()
    val learners = arrayListOf<learner>()

    init {
        skillsInitializer()
        learnersInitializer()
    }

    private fun learnersInitializer() {
        var learner = learner("jade Test",200,"Benin")
        learners.add(learner)
        learner = learner("John Doe",300,"togo")
        learners.add(learner)
        learner = learner("jane Love",400,"Tokyo")
        learners.add(learner)
    }

    private fun skillsInitializer() {
        var skill = skill("jade Test",79,"Benin")
        skills.add(skill)
        skill = skill("John Doe",58,"togo")
        skills.add(skill)
        skill = skill("jane Love",85,"Tokyo")
        skills.add(skill)
    }
}