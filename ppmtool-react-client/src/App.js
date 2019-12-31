import React from "react";
import "./App.css";
import Dashborad from "./compoments/Dashborad";
import Header from "./compoments/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <div className="App">
      <Header />
      <Dashborad />
    </div>
  );
}

export default App;
