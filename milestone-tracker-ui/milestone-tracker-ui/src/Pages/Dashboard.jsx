import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Confetti from 'react-confetti';
import { useWindowSize } from 'react-use';


const Dashboard = () => {
    const [milestones, setMilestones] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newMilestone, setNewMilestone] = useState({ title: '', totalDays: '' });
    const [loading, setLoading] = useState(true);
    const { width, height } = useWindowSize();
    const [showConfetti, setShowConfetti] = useState(false);


    const token = localStorage.getItem('token');

    const fetchMilestones = async () => {
        setLoading(true);
        try {
            const res = await axios.get('http://localhost:8080/milestone', {
                headers: { Authorization: `Bearer ${token}` },
            });
            setMilestones(res.data);
        } catch (err) {
            alert('Error fetching milestones');
        }
        setLoading(false);
    };


    const handleAdd = async () => {
        if (!newMilestone.title || !newMilestone.totalDays) {
            alert('Please fill all fields');
            return;
        }

        try {
            await axios.post('http://localhost:8080/milestoneController/create', newMilestone, {
                headers: { Authorization: `Bearer ${token}` },
            });
            setShowModal(false);
            setNewMilestone({ title: '', totalDays: '' });
            fetchMilestones();
        } catch (err) {
            alert('Failed to create milestone');
        }
    };

    const handleLogHours = async (milestoneId, hours) => {
        if (!hours || hours <= 0) {
            alert("Please enter valid hours.");
            return;
        }

        try {
            await axios.post(`http://localhost:8080/dailylogController/add/${milestoneId}`, {
                hoursWorked: hours,
            }, {
                headers: { Authorization: `Bearer ${token}` },
            });

            alert("Logged hours successfully!");
            fetchMilestones(); // refresh data
            if ((res?.data?.dailyLogs?.length || 0) === res?.data?.totalDays) {
                setShowConfetti(true);
                setTimeout(() => setShowConfetti(false), 5000); // hide after 5 sec
            }

        } catch (err) {
            alert("Error logging hours. Possibly already logged today.");
        }
    };

    const handleDelete = async (milestoneId) => {
        try {
            await axios.delete(`http://localhost:8080/milestoneController/${milestoneId}`, {
                headers: { Authorization: `Bearer ${token}` },
            });
            alert('Milestone deleted');
            fetchMilestones(); // refresh list
        } catch (err) {
            alert('Error deleting milestone');
        }
    };



    useEffect(() => {
        fetchMilestones();
    }, []);

    return (
        <>
            {/* Navbar */}
            <div className="bg-white shadow p-4 flex justify-between items-center sticky top-0 z-50">
                <h1 className="text-2xl font-bold text-indigo-600">🎯 MilestoneMate</h1>
                <button
                    onClick={() => {
                        localStorage.removeItem('token');
                        window.location.href = '/';
                    }}
                    className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
                >
                    Logout
                </button>
            </div>
            <div className="max-w-4xl mx-auto mt-10 bg-indigo-100 p-6 rounded-xl shadow text-center">
                <h2 className="text-2xl font-semibold text-indigo-700 mb-2">Keep pushing forward!</h2>
                <p className="text-gray-700 italic">
                    “Small progress is still progress. Stay consistent and your milestones will follow.”
                </p>
            </div>


            <div className="grid grid-cols-1 sm:grid-cols-3 gap-4 my-8 text-center">
                <div className="bg-white rounded-lg shadow-md p-6">
                    <h3 className="text-lg font-semibold text-gray-700">Total Milestones</h3>
                    <p className="text-2xl text-indigo-600">{milestones.length}</p>
                </div>
                <div className="bg-white rounded-lg shadow-md p-6">
                    <h3 className="text-lg font-semibold text-gray-700">Total Days Planned</h3>
                    <p className="text-2xl text-indigo-600">
                        {milestones.reduce((acc, m) => acc + Number(m.totalDays), 0)}
                    </p>
                </div>
                <div className="bg-white rounded-lg shadow-md p-6">
                    <h3 className="text-lg font-semibold text-gray-700">Days Logged</h3>
                    <p className="text-2xl text-indigo-600">
                        {milestones.reduce((acc, m) => acc + (m.dailyLogs?.length || 0), 0)}
                    </p>
                </div>
            </div>


            <div className="min-h-screen bg-gradient-to-br from-indigo-50 to-blue-100 px-4 py-10">

                <div className="max-w-4xl mx-auto bg-white p-8 rounded-2xl shadow-xl relative">
                    <h1 className="text-3xl font-bold mb-2 text-gray-800">Your Dashboard</h1>
                    <p className="text-gray-500 mb-8">Track your milestones & progress every day 🚀</p>

                    {/* Motivational Card */}
                    <div className="bg-indigo-50 p-6 rounded-xl shadow-md mb-6 flex items-center justify-between">
                        <div>
                            <h3 className="text-xl font-bold text-indigo-700">Welcome, Achiever! 🌟</h3>
                            <p className="text-gray-600 mt-1">Stay consistent — each log brings you closer to success.</p>
                        </div>
                        <img
                            src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
                            alt="User"
                            className="w-16 h-16 rounded-full border-2 border-indigo-400"
                        />
                    </div>


                    <div className="flex justify-between items-center mb-6">
                        <h2 className="text-xl font-semibold text-gray-700">Your Milestones</h2>
                        <button
                            onClick={() => setShowModal(true)}
                            className="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-4 py-2 rounded-lg"
                        >
                            + Add Milestone
                        </button>
                    </div>

                    {loading ? (
                        <div className="text-center py-20">
                            <div className="animate-spin rounded-full h-16 w-16 border-b-4 border-indigo-600 mx-auto mb-4"></div>
                            <p className="text-indigo-600 font-semibold text-lg">Loading your progress...</p>
                        </div>
                    ) : milestones.length === 0 ? (
                        <p className="text-gray-400">No milestones yet.</p>
                    ) : (
                        <ul className="space-y-4">
                            {milestones.map((m) => (
                                <li key={m.id} className="border p-4 rounded-xl shadow-sm bg-gray-50">
                                    <h3 className="text-lg font-medium">{m.title}</h3>
                                    <p className="text-sm text-gray-600 mb-2">Days left: {m.totalDays}</p>

                                    {/* Progress Bar */}
                                    <div className="mt-2">
                                        <p className="text-sm text-gray-500 mb-1">
                                            Progress: {Math.min(Math.round((m.dailyLogs?.length || 0) / m.totalDays * 100), 100)}%
                                        </p>
                                        <div className="w-full bg-gray-200 h-3 rounded-full overflow-hidden">
                                            <div
                                                className="bg-blue-600 h-full"
                                                style={{
                                                    width: `${Math.min(Math.round((m.dailyLogs?.length || 0) / m.totalDays * 100), 100)}%`,
                                                    transition: 'width 0.5s',
                                                }}
                                            />
                                        </div>
                                    </div>

                                    {/* Daily log form */}
                                    <div className="flex items-center gap-3 mt-3">
                                        <input
                                            type="number"
                                            min="1"
                                            placeholder="Hours worked today"
                                            className="border p-2 rounded w-1/2"
                                            onChange={(e) => m.todayHours = e.target.value}
                                        />
                                        <button
                                            className="bg-green-600 text-white px-3 py-2 rounded hover:bg-green-700"
                                            onClick={() => handleLogHours(m.id, m.todayHours)}
                                        >
                                            Log Hours
                                        </button>
                                    </div>

                                    {/* Recent Logs */}
                                    {m.dailyLogs && m.dailyLogs.length > 0 && (
                                        <div className="text-sm text-gray-500 mt-2">
                                            Recent Logs:
                                            <ul className="list-disc list-inside">
                                                {m.dailyLogs.slice(-5).reverse().map((log, index) => (
                                                    <li key={index}>
                                                        {log.date} — {log.hoursWorked} hrs
                                                    </li>
                                                ))}
                                            </ul>
                                        </div>
                                    )}

                                    <button
                                        className="mt-3 bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
                                        onClick={() => handleDelete(m.id)}
                                    >
                                        Delete
                                    </button>
                                </li>
                            ))}
                        </ul>
                    )}

                </div>

                {/* Modal */}
                {showModal && (
                    <div className="fixed inset-0 bg-black bg-opacity-40 flex justify-center items-center z-50">
                        <div className="bg-white p-6 rounded-xl w-full max-w-sm shadow-xl space-y-4">
                            <h2 className="text-xl font-bold">New Milestone</h2>
                            <input
                                type="text"
                                placeholder="Milestone Title"
                                className="w-full border px-4 py-2 rounded"
                                value={newMilestone.title}
                                onChange={(e) => setNewMilestone({ ...newMilestone, title: e.target.value })}
                            />
                            <input
                                type="number"
                                placeholder="Total Days"
                                className="w-full border px-4 py-2 rounded"
                                value={newMilestone.totalDays}
                                onChange={(e) => setNewMilestone({ ...newMilestone, totalDays: e.target.value })}
                            />
                            <div className="flex justify-end space-x-3">
                                <button
                                    onClick={() => setShowModal(false)}
                                    className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300"
                                >
                                    Cancel
                                </button>
                                <button
                                    onClick={handleAdd}
                                    className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
                                >
                                    Save
                                </button>
                            </div>
                        </div>
                    </div>
                )}
            </div>
        </>
    );
};

export default Dashboard;
