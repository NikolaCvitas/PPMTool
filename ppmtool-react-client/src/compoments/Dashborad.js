import React, { Component } from "react";
import ProjectItem from "./Project/ProjectItem";

export default class Dashborad extends Component {
  render() {
    return (
      <div>
        <h1>Welcome to the Dashborad.</h1>

        <ProjectItem />
      </div>
    );
  }
}
