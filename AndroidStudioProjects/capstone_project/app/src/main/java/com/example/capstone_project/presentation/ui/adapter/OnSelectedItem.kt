package com.example.capstone_project.presentation.ui.adapter

import com.example.capstone_project.data.entities.model.Book

interface OnSelectedItem {
    fun onItemListener(book: Book)
}