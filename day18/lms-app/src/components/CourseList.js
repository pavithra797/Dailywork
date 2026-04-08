import { useEffect, useState } from "react";
import { getCourses } from "../services/api";
import { Link } from "react-router-dom";

function CourseList() {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        getCourses().then(res => setCourses(res.data));
    }, []);

    return (
        <div>
            <h2>Courses</h2>
            {courses.map(course => (
                <div key={course.id}>
                    <Link to={`/course/${course.id}`}>
                        {course.title}
                    </Link>
                </div>
            ))}
        </div>
    );
}

export default CourseList;
