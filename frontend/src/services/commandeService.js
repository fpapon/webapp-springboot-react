import axios from 'axios';

// URL relative : le frontend est servi par le même backend
const apiClient = axios.create({
  baseURL: '/api',
  headers: { 'Content-Type': 'application/json' },
});

export const commandeService = {
  getAllCommandes: async () => {
    const response = await apiClient.get('/commandes');
    return response.data;
  },
};
