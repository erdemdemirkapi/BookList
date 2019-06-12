import React, { Component } from 'react';
import './App.css';
import Modal from 'react-modal';

import axios from 'axios';

const customStyles = {
  content : {
    top                   : '50%',
    left                  : '50%',
    right                 : 'auto',
    bottom                : 'auto',
    marginRight           : '-50%',
    transform             : 'translate(-50%, -50%)'
  }
};

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      items: [],
      isLoaded: false,
      modalIsOpen: false,
      newItemName: '',
      newItemAuthor: '',
      newItemPublisher: '',
      updateItemName: '',
      updateItemAuthor: '',
      updateItemPublisher: '',
    };

    this.openModal = this.openModal.bind(this);
    this.closeModal = this.closeModal.bind(this);
    this.addItem = this.addItem.bind(this);
    this.getItems = this.getItems.bind(this);
    this.updateItem = this.updateItem.bind(this);
    this.confirmDelete = this.confirmDelete.bind(this);
    this.deleteItem = this.deleteItem.bind(this);
    this.handleNameChange = this.handleNameChange.bind(this);
    this.handleAuthorChange = this.handleAuthorChange.bind(this);
    this.handlePublisherChange = this.handlePublisherChange.bind(this);

    this.handleUpdateAuthorChange = this.handleUpdateAuthorChange.bind(this);
    this.handleUpdatePublisherChange = this.handleUpdatePublisherChange.bind(this);
  }

  componentDidMount() {
    this.getItems();
  }

  handleNameChange(event) {
    this.setState({newItemName: event.target.value});
  }
  handleAuthorChange(event) {
    this.setState({newItemAuthor: event.target.value});
  }
  handlePublisherChange(event) {
    this.setState({newItemPublisher: event.target.value});
  }

  handleUpdateAuthorChange(event) {
    this.setState({updateItemAuthor: event.target.value})
  }
  handleUpdatePublisherChange(event) {
    this.setState({updateItemPublisher: event.target.value})
  }

  getItems() {
    
    axios.get('getAll')
    .then(response => {
      this.setState({
        isLoaded: true,
        items: response.data,
      })
    });
  }

  addItem(event) {
    event.preventDefault();
    const newItem = {
      name: this.state.newItemName,
      author: this.state.newItemAuthor,
      publisher: this.state.newItemPublisher,
    };
    axios.get('create', {params: newItem})
    .then(() => {
      this.setState({
        newItemName: '',
        newItemAuthor: '',
        newItemPublisher: '',
      });
      alert('Book successfully added!');
      this.getItems();
    });
  }

  confirmDelete(item) {
    if (window.confirm('Are you sure you wish to delete this book?')) this.deleteItem(item);
  }

  deleteItem(item) {
    axios.get("delete", { params: { name: item.name } })
    .then(() => {
      alert('Book deleted');
      this.getItems();
    })
  }

  updateItem(event) {
    event.preventDefault();
    const updateItem = {
      name: this.state.updateItemName,
      author: this.state.updateItemAuthor,
      publisher: this.state.updateItemPublisher,
    };
    axios.get('update', {params: updateItem})
    .then(() => {
      alert('Book updated');
      this.setState({
        updateItemName: '',
        updateItemAuthor: '',
        updateItemPublisher: '',
      });
      this.closeModal();
      this.getItems();
    });
  }

  openModal(item) {
    this.setState({
      modalIsOpen: true,
      updateItemName: item.name
    });
  }

  closeModal() {
    this.setState({modalIsOpen: false});
  }

  render() {

    var { isLoaded, items } = this.state;
    
    if( !isLoaded ){
      return <div>Loading...</div>
    }
    else { 
      return (
        <div className="App"> 
          <form onSubmit={this.addItem}>
            <label className="label">
              Name:
              <input className="form-control" type="text" name="name" value={this.state.newItemName} onChange={this.handleNameChange}/>
            </label>
            <label className="label">
              Author:
              <input className="form-control" type="text" name="author" value={this.state.newItemAuthor} onChange={this.handleAuthorChange}/>
            </label>
            <label className="label">
              Publisher:
              <input className="form-control" type="text" name="publisher" value={this.state.newItemPublisher} onChange={this.handlePublisherChange}/>
            </label>
            <button type="submit" className="btn btn-primary">Add new book</button>
          </form>

          <ul className="list-group list-group-flush">
            {items.map(item => (
              <li className="list-group-item" key={item.id}>
                <label className="label">Name:</label> {item.name} | 
                <label className="label">Author:</label> {item.author} | 
                <label className="label">Publisher:</label> {item.publisher}
                <button className="btn btn-primary" onClick = {() => this.confirmDelete(item)}>Delete</button>
                <button className="btn btn-primary" onClick={() => this.openModal(item)}>Update</button>
              </li>
            ))}
          </ul>

          <div>
            <Modal
              isOpen={this.state.modalIsOpen}
              onRequestClose={this.closeModal}
              style={customStyles}
            >
              <h2 ref={subtitle => this.subtitle = subtitle}>Update</h2>
              <form>
                <label className="label">
                  Author:
                  <input className="form-control" type="text" name="author" value={this.state.updateItemAuthor} onChange={this.handleUpdateAuthorChange}/>
                </label>
                <label className="label">
                  Publisher:
                  <input className="form-control" type="text" name="publisher" value={this.state.updateItemPublisher} onChange={this.handleUpdatePublisherChange}/>
                </label>
                <button className="btn btn-primary" onClick={this.updateItem}>Update</button>
                <button className="btn btn-primary" onClick={this.closeModal}>Cancel</button>
              </form>
            </Modal>
          </div>

        </div>
      );
    }
  }

}

export default App;