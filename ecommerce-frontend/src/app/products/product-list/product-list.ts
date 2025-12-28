import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { ProductService } from '../product.service';
import { Product } from '../product';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];

  constructor(
    private productService: ProductService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    console.log('Loading products...');
    this.productService.list().subscribe({
      next: (data) => {
        console.log('Products received:', data);
        this.products = data;
      },
      error: (err) => {
        console.error('Error loading products:', err);
      }
    });
  }

  delete(id?: number) {
    if (!id) return;
    if (confirm("Supprimer ce produit ?")) {
      this.productService.delete(id).subscribe(() => this.loadProducts());
    }
  }

  edit(id: number) {
    this.router.navigate(['/products/edit', id]);
  }
}
