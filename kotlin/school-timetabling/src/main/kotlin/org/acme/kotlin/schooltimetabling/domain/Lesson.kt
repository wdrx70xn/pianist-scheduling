package org.acme.kotlin.schooltimetabling.domain

import ai.timefold.solver.core.api.domain.entity.PlanningEntity
import ai.timefold.solver.core.api.domain.lookup.PlanningId
import ai.timefold.solver.core.api.domain.variable.PlanningVariable
import com.fasterxml.jackson.annotation.JsonIdentityReference


@PlanningEntity
data class Lesson (
    @PlanningId
    val id: String,
    val subject: String,
    val studentGroup: String
) {

    @JsonIdentityReference
    @PlanningVariable
    var timeslot: Timeslot? = null

    @JsonIdentityReference
    @PlanningVariable
    var teacher: Teacher? = null

    constructor(id: String, subject: String, studentGroup: String, timeslot: Timeslot?, teacher: Teacher?)
            : this(id, subject, studentGroup) {
        this.timeslot = timeslot
        this.teacher = teacher
    }

    override fun toString(): String = "$subject($id)"

}
