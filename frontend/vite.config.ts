// ------------------------------------------------------------------------------------------------------

// Version 1.1.0
// Created by Guacamoleboy
// Last Updated: 20/08-2026

// ------------------------------------------------------------------------------------------------------

import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  resolve: {
    tsconfigPaths: true,
  },
})