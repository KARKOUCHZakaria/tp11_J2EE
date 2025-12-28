import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { CategoryService } from '../category.service';
import { Category } from '../category';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './category-list.html',
  styleUrl: './category-list.css',
})
export class CategoryListComponent implements OnInit {

  categories: Category[] = [];

  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories() {
    console.log('Loading categories...');
    this.categoryService.list().subscribe({
      next: (data) => {
        console.log('Categories received:', data);
        this.categories = data;
      },
      error: (err) => {
        console.error('Error loading categories:', err);
      }
    });
  }

  delete(id?: number) {
    if (!id) return;
    if (confirm("Supprimer cette catégorie ?")) {
      this.categoryService.delete(id).subscribe(() => this.loadCategories());
    }
  }

  edit(id: number) {
    this.router.navigate(['/categories/edit', id]);
  }
}
