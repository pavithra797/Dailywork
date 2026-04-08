import { NavLink } from "react-router-dom";

function Navbar() {
    return (
        <nav className="navbar">
            <NavLink to="/" className="nav-item">
                Home
            </NavLink>

            <NavLink to="/add" className="nav-item">
                Add Note
            </NavLink>

            <NavLink to="/list" className="nav-item">
                List Notes
            </NavLink>
        </nav>
    );
}

export default Navbar;