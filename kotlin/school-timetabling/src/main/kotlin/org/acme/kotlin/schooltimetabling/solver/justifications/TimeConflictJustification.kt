package org.acme.kotlin.schooltimetabling.solver.justifications

import ai.timefold.solver.core.api.score.stream.ConstraintJustification
import org.acme.kotlin.schooltimetabling.domain.Lesson

data class TimeConflictJustification(
    val lesson1: Lesson,
    val lesson2: Lesson,
    val description: String
) :
    ConstraintJustification {

    constructor(
        lesson1: Lesson,
        lesson2: Lesson
    ) : this(
        lesson1, lesson2, "Lessons '%s' for student group '%s' and '%s' for student group '%s' have the same timeslot at '%s %s'"
            .format(
                lesson1.subject,
                lesson1.studentGroup,
                lesson2.subject,
                lesson2.studentGroup,
                lesson1.timeslot?.dayOfWeek,
                lesson1.timeslot?.startTime
            )
    )
}
