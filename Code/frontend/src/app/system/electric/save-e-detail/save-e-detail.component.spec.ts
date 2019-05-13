import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveEDetailComponent } from './save-e-detail.component';

describe('SaveEDetailComponent', () => {
  let component: SaveEDetailComponent;
  let fixture: ComponentFixture<SaveEDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveEDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveEDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
