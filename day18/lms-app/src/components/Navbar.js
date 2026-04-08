import { Link } from "react-router-dom";

function Navbar() {
    return (
        <nav>
            <Link to="/">Courses</Link> |
            <Link to="/add">Add course</Link>
        </nav>
    );
}
export default Navbar;