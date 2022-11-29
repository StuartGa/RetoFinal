package com.example.capstone_project.presentation.ui.adapter

import com.example.capstone_project.domain.model.BookDomain

interface OnSelectedItem {
    fun onItemListener(book: BookDomain)
}