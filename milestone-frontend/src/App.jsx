const { Router, Route } = require("react-router-dom");

function App(){
    return(
        <Router>
            <Routes>
                <Route path="/"  element = {<Login/>} />
                <Route path="/signup" element = {<Signup/>} />
                <Route path = "/dashboard" element = {<Dashboard/>} />
            </Routes>
        </Router>
    );
}

export default App;