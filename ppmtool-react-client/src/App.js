import React from "react";
import "./App.css";
import Dashboard from "./compoments/Dashboard";
import Header from "./compoments/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./compoments/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./compoments/Project/UpdateProject";
import ProjectBoard from "./compoments/ProjectBoard/ProjectBoard";
import AddProjectTask from "./compoments/ProjectBoard/ProjectTasks/AddProjectTask";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/addProject" component={AddProject} />
          <Route exact path="/updateProject/:id" component={UpdateProject} />
          <Route exact path="/projectBoard/:id" component={ProjectBoard} />
          <Route exact path="/addProjectTask/:id" component={AddProjectTask} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
