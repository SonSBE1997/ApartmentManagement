import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveTypeComponent } from './save-type.component';

describe('SaveTypeComponent', () => {
  let component: SaveTypeComponent;
  let fixture: ComponentFixture<SaveTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
