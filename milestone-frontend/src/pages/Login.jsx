import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const [formData, setFormData] = useState({ email: '', password: '' });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({...formData, [e.target.name]: e.target.value});
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('http://localhost:8080/auth/login', formData);
      localStorage.setItem('token', res.data.token);
      alert('Welcome back!');
      navigate('/dashboard');
    } catch (err) {
      alert('Login failed: ' + err.response?.data || err.message);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-purple-50 to-blue-100 flex items-center justify-center px-4">
      <div className="w-full max-w-md bg-white p-8 rounded-2xl shadow-xl animate-fade-in">
        <h1 className="text-4xl font-extrabold text-center text-gray-800 mb-2">Welcome Back 👋</h1>
        <p className="text-center text-gray-500 mb-6">Log in to continue your milestones</p>

        <form onSubmit={handleSubmit} className="space-y-5">
          <div>
            <label className="block text-sm font-medium text-gray-700">Email</label>
            <input
              type="email"
              name="email"
              required
              onChange={handleChange}
              className="w-full mt-1 p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
              placeholder="you@example.com"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">Password</label>
            <input
              type="password"
              name="password"
              required
              onChange={handleChange}
              className="w-full mt-1 p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
              placeholder="••••••••"
            />
          </div>

          <button
            type="submit"
            className="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 rounded-lg transition duration-300"
          >
            Login
          </button>
        </form>

        <p className="text-xs text-center text-gray-400 mt-6">
          "A goal properly set is halfway reached." – Zig Ziglar
        </p>
      </div>
    </div>
  );
};

export default Login;
